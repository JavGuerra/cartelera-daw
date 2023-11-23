package cartelera.services.impl;

import cartelera.entities.Room;
import cartelera.repositories.RoomRepository;
import cartelera.services.IRoomService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static cartelera.utils.Utils.invalidPosNumber;

@Slf4j
@AllArgsConstructor
@Service
public class RoomServiceImpl implements IRoomService {

    private final RoomRepository roomRepo;

    @Override
    public List<Room> findAll() {
        log.info("findAll");
        return roomRepo.findAll();
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
    public List<Room> findAllByCinemaId(Long id) {
        log.info("findAllByCinemaId {}", id);
        if (invalidPosNumber(id)) return new ArrayList<>();
        return roomRepo.findAllByCinema_Id(id);
    }

    @Override
    public List<Room> findAllByFilmId(Long id) {
        log.info("findAllByFilmId {}", id);
        if (invalidPosNumber(id)) return new ArrayList<>();
        return roomRepo.findAllByFilm_Id(id);
    }

    @Override
    public Room save(Room room) {
        log.info("save {}", room);
        return roomRepo.save(room);
    }

    @Override
    public void deleteById(Long id) {
        log.info("deleteById {}", id);
        if (invalidPosNumber(id) && !roomRepo.existsById(id)) return;
        // desasociar room de cine
        Room room = findById(id).get();
        room.setCinema(null);
        room.setFilm(null);
        roomRepo.deleteById(id);
    }
}


