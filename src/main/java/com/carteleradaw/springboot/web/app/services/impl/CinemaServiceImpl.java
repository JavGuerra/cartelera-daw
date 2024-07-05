package com.carteleradaw.springboot.web.app.services.impl;

import com.carteleradaw.springboot.web.app.entities.Address;
import com.carteleradaw.springboot.web.app.entities.Cinema;
import com.carteleradaw.springboot.web.app.repositories.CinemaRepository;
import com.carteleradaw.springboot.web.app.repositories.RoomRepository;
import com.carteleradaw.springboot.web.app.services.IAddressService;
import com.carteleradaw.springboot.web.app.services.ICinemaService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.carteleradaw.springboot.web.app.utils.Utils.*;

/**
 * Implementación de servicios de cines.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CinemaServiceImpl implements ICinemaService {

    private final HttpSession session;
    private final IAddressService addressService;
    private final CinemaRepository cinemaRepo;
    private final RoomRepository roomRepo;

    @Override
    public List<Cinema> findAll() {
        if (isAuth()) return cinemaRepo.findAll();
        else return cinemaRepo.findAllByCinemaAndActiveTrue();
    }

    @Override
    public boolean isVisible(Long id) {
        log.info("isActive {}", id);
        if (invalidPosNumber(id)) return false;
        if (isAuth()) return true;
        if (existsById(id)) return findById(id).get().getActive();
        return false;
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
    public boolean existsByCif(String cif) {
        log.info("existsByCif {}", cif);
        if (stringIsEmpty(cif)) return false;
        return cinemaRepo.findByCifIgnoreCase(cif).isPresent();
    }

    @Override
    public Optional<Cinema> findByCif(String cif) {
        log.info("findByCif {}", cif);
        if (stringIsEmpty(cif)) return Optional.empty();
        return cinemaRepo.findByCifIgnoreCase(cif);
    }

    @Override
    public Page<Cinema> findAllByCity(String city, Pageable paging) {
        log.info("findAllByCity {}", city);
        if (stringIsEmpty(city)) return (isAuth()) ? cinemaRepo.findAll(paging) : cinemaRepo.findAllByActiveTrue(paging);
        return (isAuth()) ?
                cinemaRepo.findAllByCityIgnoreCase(city, paging) : cinemaRepo.findAllByCityIgnoreCaseAndActiveTrue(city, paging);
    }

    @Override
    @Transactional
    public Cinema save(Cinema cinema) {
        log.info("save {}", cinema);

        String message = "";

        try {
            Address address = cinema.getAddress();

            if (addressService.existsById(address.getId())) addressService.save(address);

            if (cinema.getActive()) {
                if (existsById(cinema.getId())) { // ¿existe ya?
                    if (roomRepo.findAllByCinema_IdAndActiveTrue(cinema.getId()).isEmpty()) cinema.setActive(false); // ¿sin salas?
                } else cinema.setActive(false);

                if (!cinema.getActive()) {
                    message = " No activado porque no tiene salas activas.";
                }
            }

            Cinema newCinema = cinemaRepo.save(cinema);

            if (!Objects.equals(address.getCity(), (String) session.getAttribute("selectedCity"))) {
                session.setAttribute("citiesNames", addressService.getCitiesNames());
                session.setAttribute("selectedCity", address.getCity());
            }

            session.setAttribute("message", "Cine " + newCinema + " guardado." + message);
            session.setAttribute("messageType", "info");

            return newCinema;

        } catch (DataIntegrityViolationException e) {
            log.error("Error al guardar el cine: ", e);

            session.setAttribute("message", "El cine no ha podido guardarse.");
            session.setAttribute("messageType", "danger");

            return null;
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("deleteById {}", id);

        if (invalidPosNumber(id) || !cinemaRepo.existsById(id)) {
            session.setAttribute("message", "Cine no encontrado.");
            session.setAttribute("messageType", "danger");
            return;
        }

        try {
            Cinema cinema = findById(id).get();
            String city = cinema.getCity();
            Integer numRooms = cinema.getCountRooms();
            String message = (numRooms > 0) ? " Salas borradas." : "";

            cinemaRepo.deleteById(id); // Y borra rooms en cascada.

            // Si ya no hay cines en una ciudad, entonces cambiar selectedCity y actualizar lista de ciudades.
            if (findAllByCity(city, Pageable.unpaged()).getContent().isEmpty()) {
                session.setAttribute("selectedCity", "");
                session.setAttribute("citiesNames", addressService.getCitiesNames());
            }

            session.setAttribute("message", "Cine " + cinema + " borrado." + message);
            session.setAttribute("messageType", "info");

        } catch (DataIntegrityViolationException e) {
            log.error("Error al borrar el cine: ", e);

            session.setAttribute("message", "El cine no ha podido borrarse.");
            session.setAttribute("messageType", "danger");
        }
    }
}
