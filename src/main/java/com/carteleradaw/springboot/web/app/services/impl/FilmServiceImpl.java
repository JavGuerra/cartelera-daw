package com.carteleradaw.springboot.web.app.services.impl;

import com.carteleradaw.springboot.web.app.entities.Film;
import com.carteleradaw.springboot.web.app.entities.Room;
import com.carteleradaw.springboot.web.app.repositories.FilmRepository;
import com.carteleradaw.springboot.web.app.repositories.RoomRepository;
import com.carteleradaw.springboot.web.app.services.IFilmService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.carteleradaw.springboot.web.app.utils.Utils.*;

@Slf4j
@AllArgsConstructor
@Service
public class FilmServiceImpl implements IFilmService {

    private final FilmRepository filmRepo;
    private final RoomRepository roomRepo;

    @Override
    public List<Film> findAll() {
        log.info("findAll");
        return filmRepo.findAll();
    }

    @Override
    public List<Film> findAllActive() {
        log.info("findAllActive");
        return filmRepo.findAllByActiveTrue();
    }

    @Override
    public boolean isVisible(Long id) {
        log.info("isActive {}", id);
        if (invalidPosNumber(id)) return false;
        if (isAuth()) return true;
        else if (filmRepo.existsById(id)) {
                return filmRepo.findById(id).get().getActive();
            } else return false;
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
        if (stringIsEmpty(city)) return (isAuth()) ? filmRepo.findAll(paging) : filmRepo.findAllByActiveTrue(paging);
        else return (isAuth()) ?
                filmRepo.findAllByCityInFilms(city, paging) : filmRepo.findAllByCityInFilmsAndActiveTrue(city, paging);
    }

//    @Override
//    public List<Film> findAllByGenre(String genre) {
//        log.info("findAllByGenre {}", genre);
//        if (stringIsEmpty(genre)) {
//            return this.findAll();
//        } else {
//            return filmRepo.findByGenreInFilms(genre);
//        }
//    }

    @Override
    public Film save(Film film) {
        log.info("save {}", film);
        return filmRepo.save(film);
    }

    @Override
    public void deactiveById(Long id) {
        log.info("deactiveById {}", id);
        if (invalidPosNumber(id)) return;
        // Desasociar film de rooms.
        List<Room> rooms = roomRepo.findAllByFilm_Id(id);
        for (Room room : rooms) {
            room.setFilm(null);
            room.setPremiere(null);
            room.setSchedules(new ArrayList<>());
            room.setActive(false);
        }

    }

    @Override
    public void deleteById(Long id) {
        log.info("deleteById {}", id);
        if (invalidPosNumber(id)) return;
        // Desasociar film de rooms.
        List<Room> rooms = roomRepo.findAllByFilm_Id(id);
        for (Room room : rooms) {
            room.setFilm(null);
            room.setPremiere(null);
            room.setSchedules(new ArrayList<>());
            room.setActive(false);
        }

        filmRepo.deleteById(id);
    }
}
