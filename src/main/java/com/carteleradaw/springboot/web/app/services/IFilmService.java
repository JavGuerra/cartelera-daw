package com.carteleradaw.springboot.web.app.services;

import com.carteleradaw.springboot.web.app.entities.Film;

import java.util.List;
import java.util.Optional;

public interface IFilmService {

    /**
     * Obtiene una lista completa de películas.
     * @return Lista de películas.
     */
    List<Film> findAll();

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
     * @return Lista de películas.
     */
    List<Film> findAllByCity(String city);

    /**
     * Obtiene la lista de películas con el género dado.
     * @param gender el género.
     * @return Lista de películas.
     */
//     List<Film> findAllByGender(String gender);

    /**
     * Guarda una película.
     * @param film Película.
     * @return La película guardada.
     */
    Film save(Film film);

    /**
     *  borra una dirección por el ID.
     * @param id Identificador.
     */
    void deleteById(Long id);
}
