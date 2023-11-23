package cartelera.services.impl;

import cartelera.entities.Film;
import cartelera.entities.Room;
import cartelera.repositories.FilmRepository;
import cartelera.repositories.RoomRepository;
import cartelera.services.IFilmService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static cartelera.utils.Utils.invalidPosNumber;

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

//    @Override
//    public List<Film> findAllByRoomsCinemaAddressCityIgnoreCase(String city) {
//        log.info("findByRoomsCinemaAddressCityIgnoreCase {}", city);
//        if (city == null || city.trim().isEmpty()) return new ArrayList<>();
//        return filmRepo.findAllByRooms_Cinema_Address_CityIgnoreCase(city);
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
        // desasociar film de rooms
        List<Room> rooms = roomRepo.findAllByFilm_Id(id);
        for (Room room : rooms) room.setFilm(null);

        filmRepo.deleteById(id);
    }

//    @Override
//    public List<Film> findAllByGenders(String gender) {
//        return filmRepo.findAllByGenders(gender);
//    }
}
