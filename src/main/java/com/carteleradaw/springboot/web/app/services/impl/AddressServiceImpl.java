package com.carteleradaw.springboot.web.app.services.impl;

import com.carteleradaw.springboot.web.app.entities.Address;
import com.carteleradaw.springboot.web.app.repositories.AddressRepository;
import com.carteleradaw.springboot.web.app.repositories.CinemaRepository;
import com.carteleradaw.springboot.web.app.services.IAddressService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import static com.carteleradaw.springboot.web.app.utils.Utils.*;

import java.util.*;

/**
 * Implementación de servicios de direcciones.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements IAddressService {

    private final HttpSession session;
    private final AddressRepository addressRepo;
    private final CinemaRepository cinemaRepo;

    @Override
    public List<Address> findAll() {
        log.info("findAll");
        return addressRepo.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        log.info("existsById {}", id);
        if (invalidPosNumber(id)) return false;
        return addressRepo.existsById(id);
    }

    @Override
    public Optional<Address> findById(Long id) {
        log.info("findById {}", id);
        if (invalidPosNumber(id)) return Optional.empty();
        return addressRepo.findById(id);
    }

    @Override
    public boolean existsCity(String city) {
        log.info("existsCity {}", city);
        return !stringIsEmpty(city) && !addressRepo.findAllByCityIgnoreCase(city).isEmpty();
    }

    @Override
    public Set<String> getCitiesNames() {
        log.info("citiesNames");
        Set<String> citiesNames = new HashSet<>();
        List<Address> addresses = isAuth() ? addressRepo.findAll() : addressRepo.findAllByCinemaActiveTrue();
        for (Address address : addresses) citiesNames.add(address.getCity());
        return citiesNames;
    }

    @Override
    @Transactional
    public Address save(Address address) {
        log.info("save {}", address);

        try {
            Address newAddress = addressRepo.save(address);

            // Al crear o actualizar la dirección actualiza selectedCity y la lista de ciudades.
            session.setAttribute("selectedCity", address.getCity());
            session.setAttribute("citiesNames", getCitiesNames());

            session.setAttribute("message", "Dirección " + address + " guardada correctamente.");
            session.setAttribute("messageType", "info");

            return newAddress;

        } catch (DataIntegrityViolationException e) {
            log.error("Error al guardar la dirección: ", e);

            session.setAttribute("message", "La dirección no ha podido guardarse.");
            session.setAttribute("messageType", "danger");

            return null;
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("deleteById {}", id);

        if (invalidPosNumber(id) || !existsById(id)) {
            session.setAttribute("message", "Dirección no encontrada.");
            session.setAttribute("messageType", "danger");
            return;
        }

        try {
            Address address = findById(id).get();
            String city = address.getCity();

            if (cinemaRepo.findByAddress_Id(id) != null) cinemaRepo.findByAddress_Id(id).setAddress(null);
            addressRepo.deleteById(id);

            // Si ya no hay direcciones con esta ciudad, entonces cambiar selectedCity y actualizar lista de ciudades.
            if (!existsCity(city)) {
                session.setAttribute("selectedCity", "");
                session.setAttribute("citiesNames", getCitiesNames());
            }

            session.setAttribute("message", "Dirección " + address + " borrada correctamente.");
            session.setAttribute("messageType", "info");

        } catch (DataIntegrityViolationException e) {
            log.error("Error al borrar la dirección: ", e);

            session.setAttribute("message", "La dirección no ha podido borrarse.");
            session.setAttribute("messageType", "danger");
        }
    }
}
