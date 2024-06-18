package com.carteleradaw.springboot.web.app.services.impl;

import com.carteleradaw.springboot.web.app.entities.Address;
import com.carteleradaw.springboot.web.app.entities.Cinema;
import com.carteleradaw.springboot.web.app.repositories.AddressRepository;
import com.carteleradaw.springboot.web.app.repositories.CinemaRepository;
import com.carteleradaw.springboot.web.app.repositories.RoomRepository;
import com.carteleradaw.springboot.web.app.services.GlobalStateService;
import com.carteleradaw.springboot.web.app.services.ICinemaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.carteleradaw.springboot.web.app.utils.Utils.*;


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
        if (isAuth()) return cinemaRepo.findAll();
        else return cinemaRepo.findAllByCinemasAndActiveTrue();
    }

    @Override
    public boolean isVisible(Long id) {
        log.info("isActive {}", id);
        if (invalidPosNumber(id)) return false;
        if (isAuth()) return true;
        else if (this.existsById(id)) {
            return this.findById(id).get().getActive();
        } else return false;
    }

    @Override
    public boolean existsById(Long id) {
        log.info("existsById {}", id);
        if (invalidPosNumber(id)) return false;
        return cinemaRepo.existsById(id);
    }

    @Override
    public boolean existsByCif(String cif) {
        log.info("existsByCif {}", cif);
        if (stringIsEmpty(cif)) return false;
        else return cinemaRepo.findByCif(cif).isPresent();
    }

    @Override
    public Optional<Cinema> findById(Long id) {
        log.info("findById {}", id);
        if (invalidPosNumber(id)) return Optional.empty();
        return cinemaRepo.findById(id);
    }

    @Override
    public Page<Cinema> findAllByCity(String city, Pageable paging) {
        log.info("findAllByCity {}", city);
        if (stringIsEmpty(city)) return (isAuth()) ?
                cinemaRepo.findAll(paging) : cinemaRepo.findAllByActiveTrue(paging);
        else return (isAuth()) ?
                cinemaRepo.findAllByCity(city, paging) : cinemaRepo.findAllByCityAndActiveTrue(city, paging);
    }

    @Override
    public Cinema save(Cinema cinema) {
        log.info("save {}", cinema);
        Address address = cinema.getAddress();
        if(!invalidPosNumber(address.getId()) && addressRepo.existsById(address.getId()))
            addressRepo.save(address);

        if (cinema.getActive()) {
            if (existsById(cinema.getId())) {
                if (roomRepo.findAllByCinema_Id(cinema.getId()).isEmpty()) {
                    cinema.setActive(false);
                }
            } else {
                cinema.setActive(false);
            }
        }

        Cinema newCinema = cinemaRepo.save(cinema);

        globalStateService.setSelectedCity(address.getCity());
        globalStateService.updateCitiesNames();

        return newCinema;
    }

    @Override
    public void deleteById(Long id) {
        log.info("deleteById {}", id);
        if (invalidPosNumber(id) && !cinemaRepo.existsById(id)) return;

        Cinema cinema = findById(id).get();
        String city = cinema.getAddress().getCity();

        cinemaRepo.deleteById(id);

        // Si ya no hay cines en una ciudad, entonces cambiar selectedCity y actualizar lista de ciudades.
        if (findAllByCity(city, Pageable.unpaged()).getContent().isEmpty()) {
            globalStateService.setSelectedCity("");
            globalStateService.updateCitiesNames();
        }
    }
}
