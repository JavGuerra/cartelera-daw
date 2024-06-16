package com.carteleradaw.springboot.web.app.services;

import com.carteleradaw.springboot.web.app.entities.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface IRoomService {

    /**
     * Obtiene la lista completa de salas.
     * @return Lista de salas.
     */
    List<Room> findAll();

    /**
     * Informa de si una sala está visible o no.
     * @param id Identificador.
     * @return Verdadero si está visible, falso en caso contrario.
     */
    boolean isVisible(Long id);

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
     * Obtiene el id no usado más bajo.
     * @return id.
     */
    Byte getNextRoomNumber();

    /**
     * Obtiene la lista de salas de una ciudad.
     * @param city Ciudad seleccionada.
     * @param paging número y tamaño de página para paginación.
     * @return Lista de salas de cine.
     */
    Page<Room> findAllByCity(String city, Pageable paging);

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
     * @param id Identificador.
     * @param selectedCity Ciudad seleccionada.
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
     * Desactiva las salas por el Id del Cine.
     * @param id identificador.
     */
    void deactiveAllByCinemaId(Long id);

    /**
     * Borra una sala por el ID.
     * @param id Identificador.
     */
    void deleteById(Long id);

    /**
     * Genera una lista de horarios con una hora de inicio y un intervalo-
     * @param startTime La hora de comienzo de emisiones.
     * @param interval El intervalo entre emisiones.
     * @return Listado de horas.
     */
    List<LocalTime> generateSchedulesList(String startTime, long interval);
}
