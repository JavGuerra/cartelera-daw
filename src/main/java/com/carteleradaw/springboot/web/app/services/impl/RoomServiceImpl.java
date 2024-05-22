package com.carteleradaw.springboot.web.app.services.impl;

import com.carteleradaw.springboot.web.app.entities.Cinema;
import com.carteleradaw.springboot.web.app.entities.Room;
import com.carteleradaw.springboot.web.app.repositories.RoomRepository;
import com.carteleradaw.springboot.web.app.services.IRoomService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.carteleradaw.springboot.web.app.utils.Utils.invalidPosNumber;
import static com.carteleradaw.springboot.web.app.utils.Utils.stringIsEmpty;

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
    public List<Room> findAllByCity(String city) {
        log.info("findAllByCity {}", city);
        if (stringIsEmpty(city)) {
            return this.findAll();
        } else {
            return roomRepo.findByCityInRooms(city);
        }
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
    public List<Room> findAllByPremiereDescDistinct(String selectedCity) {
        log.info("findAllByPremiereDesc");

        List<Room> rooms = roomRepo.findAllByPremiereDesc();
        if (!stringIsEmpty(selectedCity)) rooms.removeIf(room -> !Objects.equals(room.getCity(), selectedCity));

        List<Room> filteredRooms = new ArrayList<>(); // Lista para almacenar los elementos filtrados
        Set<Long> processedFilmIds = new HashSet<>(); // Conjunto para almacenar filmId ya procesados

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


