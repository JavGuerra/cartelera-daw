package com.carteleradaw.springboot.web.app.services.impl;

import com.carteleradaw.springboot.web.app.entities.Cinema;
import com.carteleradaw.springboot.web.app.entities.Room;
import com.carteleradaw.springboot.web.app.repositories.RoomRepository;
import com.carteleradaw.springboot.web.app.services.IRoomService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static com.carteleradaw.springboot.web.app.utils.Utils.*;

/**
 * Implementación de servicios de salas.
 */
@Slf4j
@AllArgsConstructor
@Service
public class RoomServiceImpl implements IRoomService {

    private final HttpSession session;
    private final RoomRepository roomRepo;
    private AddressServiceImpl addressService;
    private CinemaServiceImpl cinemaService;
    private FilmServiceImpl filmService;

    @Override
    public List<Room> findAll() {
        log.info("findAll");
        return roomRepo.findAll();
    }

    @Override
    public boolean isVisible(Long id) {
        log.info("isActive {}", id);
        if (invalidPosNumber(id)) return false;
        if (isAuth()) return true;
        if (this.existsById(id)) return this.findById(id).get().getActive();
        return false;
    }

    @Override
    public boolean existsById(Long id) {
        log.info("existsById {}", id);
        if (invalidPosNumber(id)) return false;
        return roomRepo.existsById(id);
    }

    @Override
    public Optional<Room> findById(Long id) {
        log.info("findById {}", id);
        if (invalidPosNumber(id)) return Optional.empty();
        return roomRepo.findById(id);
    }

    @Override
    public Integer getNextRoomNumber(Long cinemaId) {

        Set<Integer> existingRoomNumbers = new HashSet<>();

        List<Room> rooms = invalidPosNumber(cinemaId) ?
                roomRepo.findAll() : roomRepo.findAllByCinema_Id(cinemaId);

        for (Room room : rooms) {
            Integer roomNumber = Integer.valueOf(room.getRoomNumber());
            existingRoomNumbers.add(roomNumber);
        }
        Integer nextRoomNumber = 1;
        while (existingRoomNumbers.contains(nextRoomNumber)) {
            nextRoomNumber++;
        }
        return nextRoomNumber;
    }

    @Override
    public Page<Room> findAllByCity(String city, Pageable paging) {
        log.info("findAllByCity {}", city);
        if (stringIsEmpty(city)) return (isAuth()) ? roomRepo.findAll(paging) : roomRepo.findAllByActiveIsTrue(paging);
        return (isAuth()) ?
                roomRepo.findAllByCinemaAddressCityIgnoreCase(city, paging) : roomRepo.findAllByCinemaAddressCityIgnoreCaseAndActiveIsTrue(city, paging);
    }

    @Override
    public Page<Room> findAllByCinemaId(Long cinemaId, Pageable paging) {
        log.info("findAllByCinemaId {}", cinemaId);
        if (invalidPosNumber(cinemaId) || !cinemaService.existsById(cinemaId)) return Page.empty();
        if (isAuth()) return roomRepo.findAllByCinema_Id(cinemaId, paging);
        return roomRepo.findAllByCinema_IdAndActiveIsTrue(cinemaId, paging);
    }

    @Override
    public List<Room> findAllByFilmId(Long filmId) {
        log.info("findAllByFilmId {}", filmId);
        if (invalidPosNumber(filmId)) return new ArrayList<>();
        return roomRepo.findAllByFilm_Id(filmId);
    }

    @Override
    public Page<Room> findAllByFilmAndCity(Long filmId, String city, Pageable paging) {
        log.info("findAllByFilmAndCity {}", filmId);
        if (invalidPosNumber(filmId) || !filmService.existsById(filmId)) return Page.empty();
        if (stringIsEmpty(city)) return (isAuth()) ?
                roomRepo.findAllByFilm_Id(filmId, paging) : roomRepo.findAllByFilm_IdAndActiveIsTrue(filmId, paging);
        return (isAuth()) ?
                roomRepo.findAllByFilm_IdAndCinemaAddressCityIgnoreCase(filmId, city, paging) : roomRepo.findAllByFilm_IdAndCinemaAddressCityIgnoreCaseAndActiveIsTrue(filmId, city, paging);
    }

    @Override
    public List<Room> findAllByPremiereDescDistinct(String city) {
        log.info("findAllByPremiereDesc");

        List<Room> rooms;
        if (stringIsEmpty(city)) rooms = roomRepo.findAllByActiveIsTrueOrderByPremiereDesc();
        else rooms = roomRepo.findAllByCinemaAddressCityIgnoreCaseAndActiveIsTrueOrderByPremiereDesc(city);

        List<Room> filteredRooms = new ArrayList<>(); // Lista para almacenar los elementos filtrados.
        Set<Long> processedFilmIds = new HashSet<>(); // Conjunto para almacenar los filmId ya procesados.
        // Elimina repeticiones, quedándose con el estreno más actual.
        for (Room room : rooms) {
            Long filmId = room.getFilmId();
            if (!processedFilmIds.contains(filmId)) {
                filteredRooms.add(room);
                processedFilmIds.add(filmId);
            }
        }

        return filteredRooms;
    }

    @Override
    @Transactional
    public Room save(Room room) {
        log.info("save {}", room);

        String message = "";

        try {
            // Si una sala no tiene película, no puede estar activa ni tener fecha de estreno.
            if (room.getFilm() == null) {
                room.setActive(false);
                room.setPremiere(null);
                // room.setSchedules(new ArrayList<>());
            }

            // Si esta sala está activada y el número de sala ya existe en ese cine,
            // entonces desactivar la otra sala activa.
            if (room.getActive()) {
                Optional<Room> optRoom = roomRepo.findByCinema_IdAndRoomNumberAndActiveIsTrue(
                        room.getCinema().getId(), room.getRoomNumber());
                if (optRoom.isPresent()) {
                    Room oldRoom = optRoom.get();
                    if ((!Objects.equals(room.getId(), oldRoom.getId())) && oldRoom.getActive()) {
                        oldRoom.setActive(false);
                        roomRepo.save(oldRoom);
                        message = " Sala " + oldRoom.getRoomNumber() + " alternativa desactivada.";
                        log.info(message);
                    }
                }
            }

            // Si una sala pertenece a un cine que está desactivado, activar el cine.
            if (room.getActive() && !room.getCinema().getActive()) {
                room.getCinema().setActive(true);
                message = " Cine " + room.getCinema() + " activado.";
                log.info(message);
            }

            // Si la sala se ha desactivado, y es la última sala activa del cine, desactivar el cine.
            if (!room.getActive() && roomRepo.findAllByCinema_IdAndActiveIsTrue(room.getCinema().getId()).size() <= 1)
                room.getCinema().setActive(false);

            // Si el cine está activo, y la ciudad es otra, se selecciona la ciudad.
            if (room.getCinema().getActive() && session.getAttribute("selectedCity") != room.getCity()) {
                session.setAttribute("citiesNames", addressService.getCitiesNames());
                session.setAttribute("selectedCity", room.getCity());
            }

            Room newRoom = roomRepo.save(room);

            session.setAttribute("message",
                    "Sala " + room.getRoomNumber()  + " de cine " + newRoom.getCinema().getName() +  " guardada." + message);
            session.setAttribute("messageType", "info");

            return newRoom;

        } catch (Exception e) {
            log.error("Error al guardar la sala: ", e);

            session.setAttribute("message", "La sala no ha podido guardarse.");
            session.setAttribute("messageType", "danger");

            return null;
        }
    }

    @Override
    @Transactional
    public void deactivateRoomsByCinemaId(Long cinemaId) {
        log.info("deactivateRoomsByCinemaId {}", cinemaId);

        if (invalidPosNumber(cinemaId)) return;

        String message = (String) session.getAttribute("message");

        try {
            if (!roomRepo.findAllByCinema_IdAndActiveIsTrue(cinemaId).isEmpty()) {
                List<Room> rooms = roomRepo.findAllByCinema_IdAndActiveIsTrue(cinemaId);
                for (Room room : rooms) room.setActive(false);

                session.setAttribute("message", message + " Salas desactivadas.");
                session.setAttribute("messageType", "info");
            }

        } catch (Exception e) {
            log.error("Error al desactivar las salas: ", e);

            session.setAttribute("message", message + " Las salas no han podido desactivarse.");
            session.setAttribute("messageType", "danger");
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("deleteById {}", id);

        if (invalidPosNumber(id) && !roomRepo.existsById(id)) {
            session.setAttribute("message", "Sala no encontrada.");
            session.setAttribute("messageType", "danger");
            return;
        }

        try {
            Room room = findById(id).get();
            Cinema cinema = room.getCinema();
            String cinemaName = cinema.getName();

            room.setCinema(null); // Desasociar room de cine
            room.setFilm(null);

            roomRepo.deleteById(id);

            // Si la sala se ha borrado, y es la última sala del cine, desactivar el cine.
            if (room.getRoomNumber() <= 1) cinema.setActive(false);

            session.setAttribute("message", "Sala " + room.getRoomNumber() + " de cine " + cinemaName + " borrada.");
            session.setAttribute("messageType", "info");

        } catch (Exception e) {
            log.error("Error al borrar la sala: ", e);

            session.setAttribute("message", "La sala no ha podido borrarse.");
            session.setAttribute("messageType", "danger");
        }
    }

    @Override
    public List<LocalTime> generateSchedulesList(String startTime, long interval) {
        List<LocalTime> scheduleList = new ArrayList<>();

        LocalTime schedule = LocalTime.parse(startTime);
        long timeRemaining = ChronoUnit.MINUTES.between(schedule, LocalTime.MAX);
        LocalTime lastSchedule = schedule.plusMinutes((timeRemaining / interval) * interval);

        scheduleList.add(schedule);
        while (schedule.isBefore(lastSchedule)) {
            schedule = schedule.plusMinutes(interval);
            scheduleList.add(schedule);
        }

        return scheduleList;
    }
}
