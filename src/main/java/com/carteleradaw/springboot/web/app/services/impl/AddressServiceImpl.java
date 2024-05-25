package com.carteleradaw.springboot.web.app.services.impl;

import com.carteleradaw.springboot.web.app.entities.Address;
import com.carteleradaw.springboot.web.app.repositories.AddressRepository;
import com.carteleradaw.springboot.web.app.repositories.CinemaRepository;
import com.carteleradaw.springboot.web.app.services.GlobalStateService;
import com.carteleradaw.springboot.web.app.services.IAddressService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import static com.carteleradaw.springboot.web.app.utils.Utils.invalidPosNumber;
import static com.carteleradaw.springboot.web.app.utils.Utils.stringIsEmpty;

import java.util.*;

@Scope("session")
@Slf4j
@AllArgsConstructor
@Service
public class AddressServiceImpl implements IAddressService {

    private final GlobalStateService globalStateService;
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
        return !stringIsEmpty(city) && !addressRepo.findByCityIgnoreCase(city).isEmpty();
    }

    @Override
    public Set<String> citiesNames() {
        log.info("citiesNames");
        Set<String> citiesNames = new HashSet<>();
        for (Address city : addressRepo.findAll()) citiesNames.add(city.getCity());
        return citiesNames;
    }

    @Override
    public Address save(Address address) {
        log.info("save {}", address);
        // Al crear o actualizar la dirección actualiza selectedCity y la lista de ciudades.
        globalStateService.setSelectedCity(address.getCity());
        globalStateService.updateCitiesNames();
        return addressRepo.save(address);
    }

    @Override
    public void deleteById(Long id) {
        log.info("deleteById {}", id);
        if (invalidPosNumber(id) && !existsById(id)) return;
        if (cinemaRepo.findByAddress_Id(id) != null) {
            cinemaRepo.findByAddress_Id(id).setAddress(null);
        }

        Optional<Address> addressOpt = this.findById(id);

        // Si ya no hay direcciones con esta ciudad, entonces cambiar selectedCity y actualizar lista de ciudades.
        if (!existsCity(addressOpt.get().getCity())) {
            globalStateService.setSelectedCity("");
            globalStateService.updateCitiesNames();
        }

        addressRepo.deleteById(id);
    }
}
