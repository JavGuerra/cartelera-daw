package com.carteleradaw.springboot.web.app.services;

import com.carteleradaw.springboot.web.app.entities.Cinema;

import java.util.List;
import java.util.Optional;

public interface ICinemaService {

    /**
     * Obtiene la lista completa de cines.
     * @return Lista de cines.
     */
    List<Cinema> findAll();

    /**
     * Comprueba si existe un cine por su ID.
     * @param id Identificador.
     * @return Verdadero si existe, falso en caso contrario.
     */
    boolean existsById(Long id);

    /**
     * Obtiene una dirección por su ID.
     * @param id Identificador.
     * @return Opcionalmente, el cine solicitado.
     */
    Optional<Cinema> findById(Long id);

    /**
     * Obtiene la lista de cines de una ciudad.
     * @return Lista de cines.
     */
    List<Cinema> findAllByCity(String city);

    /**
     * Guarda un cine.
     * @param cinema Cine.
     * @return El cine guardado.
     */
    Cinema save(Cinema cinema);

    /**
     * Borra un cine por su ID.
     * @param id Identificador.
     */
    void deleteById(Long id);
}
