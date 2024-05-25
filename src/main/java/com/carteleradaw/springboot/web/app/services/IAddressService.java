package com.carteleradaw.springboot.web.app.services;

import com.carteleradaw.springboot.web.app.entities.Address;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IAddressService {

    /**
     * Obtiene la lista completa de direcciones.
     * @return Lista de direcciones.
     */
    List<Address> findAll();

    /**
     * Comprueba si existe una dirección por su ID.
     * @param id Identificador.
     * @return Verdadero si existe, falso en caso contrario.
     */
    boolean existsById(Long id);

    /**
     * Obtiene una dirección por su ID.
     * @param id Identificador.
     * @return Opcionalmente, la dirección solicitada.
     */
    Optional<Address> findById(Long id);

    /**
     * Comprueba si existe el nombre de una ciudad en alguna dirección.
     * @param city Ciudad.
     * @return Verdadero si existe, falso en caso contrario.
     */
    boolean existsCity(String city);

    /**
     * Obtiene una lista de nombres de ciudades disponibles.
     * @return Lista de nombres de ciudades.
     */
    Set<String> citiesNames();

    /**
     * Guarda una dirección.
     * @param address Dirección.
     * @return La dirección guardada.
     */
    Address save(Address address);

    /**
     * Borra una dirección por el ID.
     * @param id Identificador.
     */
    void deleteById(Long id);
}
