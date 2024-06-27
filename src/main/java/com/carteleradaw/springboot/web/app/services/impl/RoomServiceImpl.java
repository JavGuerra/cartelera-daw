package com.carteleradaw.springboot.web.app.services.impl;

import com.carteleradaw.springboot.web.app.entities.Room;
import com.carteleradaw.springboot.web.app.repositories.RoomRepository;
import com.carteleradaw.springboot.web.app.services.IRoomService;
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

    private final RoomRepository roomRepo;
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
    public Byte getNextRoomNumber() {
        Set<Byte> existingRoomNumbers = new HashSet<>();
        for (Room room : roomRepo.findAll()) {
            Byte roomNumber = room.getRoomNumber();
            existingRoomNumbers.add(roomNumber);
        }
        Byte nextRoomNumber = 1;
        while (existingRoomNumbers.contains(nextRoomNumber)) {
            nextRoomNumber++;
        }
        return nextRoomNumber;
    }

    @Override
    public Page<Room> findAllByCity(String city, Pageable paging) {
        log.info("findAllByCity {}", city);
        if (stringIsEmpty(city)) return (isAuth()) ? roomRepo.findAll(paging) : roomRepo.findAllByActiveTrue(paging);
        return (isAuth()) ?
                roomRepo.findAllByCityIgnoreCaseInRoom(city, paging) : roomRepo.findAllByCityIgnoreCaseInRoomAndActiveTrue(city, paging);
    }

    @Override
    public Page<Room> findAllByCinemaId(Long id, Pageable paging) { // TODO ¿Devuelve vacío?
        log.info("findAllByCinemaId {}", id);
        if (invalidPosNumber(id) || !cinemaService.existsById(id)) return Page.empty();
        if (isAuth()) return roomRepo.findAllByCinema_Id(id, paging);
        return roomRepo.findAllByCinema_IdAndActiveTrue(id, paging);
    }

    @Override
    public List<Room> findAllByFilmId(Long id) {
        log.info("findAllByFilmId {}", id);
        if (invalidPosNumber(id)) return new ArrayList<>();
        return roomRepo.findAllByFilm_Id(id);
    }

    @Override
    public Page<Room> findAllByFilmAndCity(Long id, String city, Pageable paging) {
        log.info("findAllByFilmAndCity {}", id);
        if (invalidPosNumber(id) || !filmService.existsById(id)) return Page.empty();
        if (stringIsEmpty(city)) return (isAuth()) ?
                roomRepo.findAllByFilm_Id(id, paging) : roomRepo.findAllByFilm_IdAndActiveTrue(id, paging);
        return (isAuth()) ?
                roomRepo.findAllByFilm_IdAndCityIgnoreCase(id, city, paging) : roomRepo.findAllByFilm_IdAndCityIgnoreCaseAndActiveTrue(id, city, paging);
    }

    @Override
    public List<Room> findAllByPremiereDescDistinct(String city) {
        log.info("findAllByPremiereDesc");

        List<Room> rooms;
        if (stringIsEmpty(city)) rooms = (isAuth()) ?
                roomRepo.findAllByPremiereDesc() : roomRepo.findAllByPremiereDescAndActiveTrue();
        else rooms = (isAuth()) ?
                roomRepo.findAllByCityIgnoreCaseAndPremiereDesc(city) :
                roomRepo.findAllByCityIgnoreCaseAndPremiereDescAndActiveTrue(city);

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

        // Si una sala no tiene película, no puede estar activa ni tener fecha de estreno, ni horarios.
        if (room.getFilm() == null) {
            room.setActive(false);
            room.setPremiere(null);
            room.setSchedules(new ArrayList<>());
        }

        // Si una sala pertenece a un cine que está desactivado, no puede activarse.
        if (room.getActive() && !room.getCinema().getActive()) room.setActive(false);

        // Si esta sala está activada y el número de sala ya existe en ese cine,
        // entonces desactivar la otra sala activa.
        if (room.getActive()) {
            Optional<Room> optRoom = roomRepo.findRoomByCinemaIdAndRoomNumberAndActiveTrue(
                    room.getCinema().getId(), room.getRoomNumber());
            if (optRoom.isPresent()) {
                Room oldRoom = optRoom.get();
                oldRoom.setActive(false);
                roomRepo.save(oldRoom);
            }
        }

        // Si la sala se ha desactivado, y es la última sala activa del cine, desactivar el cine.
        if (!room.getActive() && roomRepo.findAllByCinema_IdAndActiveTrue(room.getCinema().getId()).size() <= 1)
            room.getCinema().setActive(false);

        return roomRepo.save(room);
    }

    @Override
    @Transactional
    public void deactivateAllByCinemaId(Long id) {
        log.info("deactivateAllByCinemaId {}", id);
        if (invalidPosNumber(id)) return;
        List<Room> rooms = roomRepo.findAllByCinema_Id(id);
        for (Room room : rooms) room.setActive(false);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("deleteById {}", id);
        if (invalidPosNumber(id) && !roomRepo.existsById(id)) return;
        // desasociar room de cine
        Room room = findById(id).get();
        room.setCinema(null);
        room.setFilm(null);
        roomRepo.deleteById(id);
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
