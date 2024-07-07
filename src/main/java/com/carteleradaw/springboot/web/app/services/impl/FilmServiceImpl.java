package com.carteleradaw.springboot.web.app.services.impl;

import com.carteleradaw.springboot.web.app.entities.Film;
import com.carteleradaw.springboot.web.app.entities.Room;
import com.carteleradaw.springboot.web.app.repositories.FilmRepository;
import com.carteleradaw.springboot.web.app.repositories.RoomRepository;
import com.carteleradaw.springboot.web.app.services.IFilmService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.carteleradaw.springboot.web.app.utils.Utils.*;

/**
 * Implementación de servicios de películas.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class FilmServiceImpl implements IFilmService {

    private final HttpSession session;
    private final FilmRepository filmRepo;
    private final RoomRepository roomRepository;
    // Nota: Se usa RoomRepository porque no es posible usar RoomService, pues se generaría una referencia circular,
    // ya que FilmService se usa ya en RoomService.

    @Override
    public List<Film> findAll() {
        log.info("findAll");
        return filmRepo.findAll();
    }

    @Override
    public List<Film> findAllActive() {
        log.info("findAllActive");
        return filmRepo.findAllByActiveIsTrue();
    }

    @Override
    public boolean isVisible(Long id) {
        log.info("isActive {}", id);
        if (invalidPosNumber(id)) return false;
        if (isAuth()) return true;
        if (filmRepo.existsById(id)) return filmRepo.findById(id).get().getActive();
        return false;
    }

    @Override
    public boolean existsById(Long id) {
        log.info("existsById {}", id);
        if (invalidPosNumber(id)) return false;
        return filmRepo.existsById(id);
    }

    @Override
    public Optional<Film> findById(Long id) {
        log.info("findById {}", id);
        if (invalidPosNumber(id)) return Optional.empty();
        return filmRepo.findById(id);
    }

    @Override
    public Page<Film> findAllByCity(String city, Pageable paging) {
        log.info("findAllByCity {}", city);
        if (stringIsEmpty(city)) return (isAuth()) ? filmRepo.findAll(paging) : filmRepo.findAllByActiveIsTrue(paging);
        return (isAuth()) ?
                filmRepo.findAllByRoomsCinemaAddressCityIgnoreCase(city, paging) : filmRepo.findAllByRoomsCinemaAddressCityIgnoreCaseAndActiveIsTrue(city, paging);
    }

    @Override
    @Transactional
    public Film save(Film film) {
        log.info("save {}", film);

        try {
        Film newFilm = filmRepo.save(film);

        session.setAttribute("message", "Película " + film + " guardada.");
        session.setAttribute("messageType", "info");

        return newFilm;

        } catch (Exception e) {
            log.error("Error al guardar la película: ", e);

            session.setAttribute("message", "La película no ha podido guardarse.");
            session.setAttribute("messageType", "danger");

            return null;
        }
    }

    @Override
    @Transactional
    public void deactivateRoomsByFilmId(Long id) {
        log.info("deactivateRoomsByFilmId {}", id);

        if (invalidPosNumber(id)) return;

        String message = (String) session.getAttribute("message");

        try {
            List<Room> rooms = roomRepository.findAllByFilm_Id(id);

            if (!rooms.isEmpty()) {
                // Desasociar film en rooms.
                for (Room room : rooms) {
                    room.setFilm(null);
                    room.setPremiere(null);
                    room.setActive(false);
                    // room.setSchedules(new ArrayList<>());
                }

                session.setAttribute("message", message + " Salas desactivadas.");
                session.setAttribute("messageType", "info");
            }

        } catch (Exception e) {
            log.error("Error al desactivar las salas: ", e);

            session.setAttribute("message", message + " Las salas no ha podido desactivarse.");
            session.setAttribute("messageType", "danger");
        }

    }

    @Override
    @Transactional
    public void deleteById(Long filmId) {
        log.info("deleteById {}", filmId);

        if (invalidPosNumber(filmId) || !filmRepo.existsById(filmId)) {
            session.setAttribute("message", "Película no encontrada.");
            session.setAttribute("messageType", "danger");
            return;
        }

        try {
            Film film = filmRepo.findById(filmId).get();

            filmRepo.deleteById(filmId);

            // Desasociar film de rooms.
            List<Room> rooms = roomRepository.findAllByFilm_Id(filmId);
            for (Room room : rooms) {
                room.setFilm(null);
                room.setPremiere(null);
                room.setSchedules(new ArrayList<>());
                room.setActive(false);
            }

            session.setAttribute("message", "Película " + film + " borrada.");
            session.setAttribute("messageType", "info");

        } catch (Exception e) {
            log.error("Error al borrar la película: ", e);

            session.setAttribute("message", "La película no ha podido borrarse.");
            session.setAttribute("messageType", "danger");
        }
    }
}
