package com.carteleradaw.springboot.web.app.services;

import com.carteleradaw.springboot.web.app.entities.Cinema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz para servicios de cines.
 */
public interface ICinemaService {

    /**
     * Obtiene la lista completa de cines.
     * @return Lista de cines.
     */
    List<Cinema> findAll();

    /**
     * Informa de si un Cine está visible o no.
     * @param id Identificador.
     * @return Verdadero si está visible, falso en caso contrario.
     */
    boolean isVisible(Long id);

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
     * Comprueba si existe un cine por su CIF.
     * @param cif Identificador.
     * @return Verdadero si existe, falso en caso contrario.
     */
    boolean existsByCif(String cif);

    /**
     * Obtiene la lista de cines de una ciudad.
     * @param city Ciudad.
     * @param paging número y tamaño de página para paginación.
     * @return Lista de cines.
     */
    Page<Cinema> findAllByCity(String city, Pageable paging);

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
