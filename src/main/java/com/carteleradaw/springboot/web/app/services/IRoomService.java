package com.carteleradaw.springboot.web.app.services;

import com.carteleradaw.springboot.web.app.entities.Cinema;
import com.carteleradaw.springboot.web.app.entities.Room;

import java.util.List;
import java.util.Optional;

public interface IRoomService {

    /**
     * Obtiene la lista completa de salas.
     * @return Lista de salas.
     */
    List<Room> findAll();

    /**
     * Cumprueba si existe una sala por su ID.
     * @param id Identificador.
     * @return Verdadero si existe, falso en caso contrario.
     */
    boolean existsById(Long id);

    /**
     * Obtiene una sala por su ID.
     * @param id Identificador.
     * @return Opcionalmente, la sala indicada.
     */
    Optional<Room> findById(Long id);

    /**
     * Obtiene la lista de salas de una ciudad.
     * @return Lista de salas de cine.
     */
    List<Room> findAllByCity(String city);

    /**
     * Obtiene la lista de salas de un cine por el ID del cine.
     * @param id Identificador.
     * @return Lista de salas de un cine.
     */
    List<Room> findAllByCinemaId(Long id);

    /**
     * Obtiene la lista de salas de una película por el ID de la película.
     * @param id Identificador.
     * @return Lista de salas de una película.
     */
    List<Room> findAllByFilmId(Long id);

    /**
     * Obtiene la lista de las salas de una ciudad que exhiben la misma película.
     * @param id identificador.
     * @param selectedCity ciudad seleccionada.
     * @return Lista de salas que contienen la misma película en la ciudad.
     */
    List<Room> findAllByFilmAndCity(Long id, String selectedCity);
    /**
     * Obrtiene la lista de salas con películas distintas por orden de estreno.
     * @param selectedCity ciudad seleccionada.
     * @return Lista de salas en orden de estreno descendente.
     */
    List<Room> findAllByPremiereDescDistinct(String selectedCity);

    /**
     * Guarda una sala.
     * @param room Sala.
     * @return La sala guardada.
     */
    Room save(Room room);

    /**
     * Borra una sala por el ID.
     * @param id Identificador.
     */
    void deleteById(Long id);
}
