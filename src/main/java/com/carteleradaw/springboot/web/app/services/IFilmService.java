package com.carteleradaw.springboot.web.app.services;

import com.carteleradaw.springboot.web.app.entities.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz para servicios de películas.
 */
public interface IFilmService {

    /**
     * Obtiene una lista completa de películas.
     * @return Lista de películas.
     */
    List<Film> findAll();

    /**
     * Obtiene una lista completa de películas activas.
     * @return Lista de películas activas.
     */
    List<Film> findAllActive();

    /**
     * Informa de si una película está visible o no.
     * @param id Identificador.
     * @return Verdadero si está visible, falso en caso contrario.
     */
    boolean isVisible(Long id);

    /**
     * Comprueba si existe una película por su ID.
     * @param id Identificador.
     * @return Verdadero si existe, falso en caso contrario.
     */
    boolean existsById(Long id);

    /**
     * Obtiene una película por si ID.
     * @param id Identificador.
     * @return Opcionalmente, la película seleccionada.
     */
    Optional<Film> findById(Long id);

    /**
     * Obtiene la lista de películas de una ciudad.
     * @param city la ciudad.
     * @param paging número y tamaño de página para paginación.
     * @return Lista de películas.
     */
    Page<Film> findAllByCity(String city, Pageable paging);

//    /**
//     * Obtiene la lista de películas con el género dado.
//     * @param genre el género.
//     * @return Lista de películas.
//     */
//     List<Film> findAllByGenre(String genre);

    /**
     * Guarda una película.
     * @param film Película.
     * @return La película guardada.
     */
    Film save(Film film);

    /**
     *  Desactiva una película por el ID.
     * @param id Identificador.
     */
    void deactivateRoomsByFilmId(Long id);

    /**
     *  Borra una película por el ID.
     * @param id Identificador.
     */
    void deleteById(Long id);
}
