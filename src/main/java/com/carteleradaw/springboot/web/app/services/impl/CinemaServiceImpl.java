package com.carteleradaw.springboot.web.app.services.impl;

import com.carteleradaw.springboot.web.app.entities.Address;
import com.carteleradaw.springboot.web.app.entities.Cinema;
import com.carteleradaw.springboot.web.app.entities.Room;
import com.carteleradaw.springboot.web.app.repositories.AddressRepository;
import com.carteleradaw.springboot.web.app.repositories.CinemaRepository;
import com.carteleradaw.springboot.web.app.repositories.RoomRepository;
import com.carteleradaw.springboot.web.app.services.GlobalStateService;
import com.carteleradaw.springboot.web.app.services.ICinemaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.carteleradaw.springboot.web.app.utils.Utils.*;

@Scope("session")
@Slf4j
@AllArgsConstructor
@Service

public class CinemaServiceImpl implements ICinemaService {

    private final GlobalStateService globalStateService;
    private final CinemaRepository cinemaRepo;
    private final RoomRepository roomRepo;
    private final AddressRepository addressRepo;

    @Override
    public List<Cinema> findAll() {
        return cinemaRepo.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        log.info("existsById {}", id);
        if (invalidPosNumber(id)) return false;
        return cinemaRepo.existsById(id);
    }

    @Override
    public Optional<Cinema> findById(Long id) {
        log.info("findById {}", id);
        if (invalidPosNumber(id)) return Optional.empty();
        return cinemaRepo.findById(id);
    }

    @Override
    public List<Cinema> findAllByCity(String city) {
        log.info("findAllByCity {}", city);
        if (stringIsEmpty(city)) {
            return this.findAll();
        } else {
            return cinemaRepo.findByCity(city);
        }
    }

    @Override
    public Cinema save(Cinema cinema) {
        log.info("save {}", cinema);
        return cinemaRepo.save(cinema);
    }

    @Override
    public void deleteById(Long id) {
        log.info("deleteById {}", id);
        if (invalidPosNumber(id) && !cinemaRepo.existsById(id)) return;

        Cinema cinema = findById(id).get();
        String city = cinema.getAddress().getCity();

        // borrar todas las rooms asociadas
        List<Room> rooms = roomRepo.findAllByCinema_Id(id);
        if (!rooms.isEmpty()) for (Room room : rooms) roomRepo.deleteById(room.getId());

        // borrar address asociada
        Address address = cinema.getAddress();
        if (address != null && addressRepo.existsById(address.getId())) {
            cinema.setAddress(null);
            addressRepo.deleteById(address.getId());
        }

        cinemaRepo.deleteById(id);

        // Si ya no hay cines en una ciudad, entonces cambiar selectedCity y actualizar lista de ciudades.
        if (findAllByCity(city).isEmpty()) {
            globalStateService.setSelectedCity("");
            globalStateService.updateCitiesNames();
        }
    }
}
