package com.carteleradaw.springboot.web.app.services.impl;

import com.carteleradaw.springboot.web.app.entities.Address;
import com.carteleradaw.springboot.web.app.repositories.AddressRepository;
import com.carteleradaw.springboot.web.app.repositories.CinemaRepository;
import com.carteleradaw.springboot.web.app.services.IAddressService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.carteleradaw.springboot.web.app.utils.Utils.invalidPosNumber;
import static com.carteleradaw.springboot.web.app.utils.Utils.stringIsEmpty;

import java.util.*;

@Slf4j
@AllArgsConstructor
@Service
public class AddressServiceImpl implements IAddressService {

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

//    public List<Address> findAllWithCinemaInfo() {
//        return addressRepo.findAllWithCinemaInfo();
//    }

//    public Address findByIdWithCinemaInfo(Long id) {
//        return addressRepo.findByIdWithCinemaInfo(id);
//    }

    @Override
    public Address save(Address address) {
        log.info("save {}", address);
        return addressRepo.save(address);
    }

    @Override
    public void deleteById(Long id) {
        log.info("deleteById {}", id);
        if (invalidPosNumber(id) && !existsById(id)) return;
        if (cinemaRepo.findByAddress_Id(id) != null)
            cinemaRepo.findByAddress_Id(id).setAddress(null);
        addressRepo.deleteById(id);
    }
}
