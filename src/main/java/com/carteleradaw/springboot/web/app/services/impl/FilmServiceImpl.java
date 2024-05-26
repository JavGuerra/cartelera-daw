package com.carteleradaw.springboot.web.app.services.impl;

import com.carteleradaw.springboot.web.app.entities.Cinema;
import com.carteleradaw.springboot.web.app.entities.Film;
import com.carteleradaw.springboot.web.app.entities.Room;
import com.carteleradaw.springboot.web.app.repositories.FilmRepository;
import com.carteleradaw.springboot.web.app.repositories.RoomRepository;
import com.carteleradaw.springboot.web.app.services.IFilmService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static com.carteleradaw.springboot.web.app.utils.Utils.invalidPosNumber;
import static com.carteleradaw.springboot.web.app.utils.Utils.stringIsEmpty;

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
    public List<Film> findAllByCity(String city) {
        log.info("findAllByCity {}", city);
        if (stringIsEmpty(city)) {
            return this.findAll();
        } else {
            return filmRepo.findByCityInFilms(city);
        }
    }

//    @Override
//    public List<Film> findAllByGender(String gender) {
//        log.info("findAllByGender {}", gender);
//        if (stringIsEmpty(gender)) {
//            return this.findAll();
//        } else {
//            return filmRepo.findByGenderInFilms(gender);
//        }
//    }

    @Override
    public Film save(Film film) {
        log.info("save {}", film);
        return filmRepo.save(film);
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
            room.setSchedules(new HashSet<>());
            room.setActive(false);
        }

        filmRepo.deleteById(id);
    }
}
