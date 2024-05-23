package com.carteleradaw.springboot.web.app.repositories;

import com.carteleradaw.springboot.web.app.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("select r from Room r where r.cinema.id = ?1")
    List<Room> findAllByCinema_Id(Long id);

    @Query("select r from Room r where r.film.id = ?1")
    List<Room> findAllByFilm_Id(Long id);

//    @Query("SELECT r FROM Room r ORDER BY r.premiere DESC")
//    List<Room> findAllByPremiereDesc();

    @Query("SELECT r FROM Room r WHERE r.active = true ORDER BY r.premiere DESC")
    List<Room> findAllActiveByPremiereDesc();

    @Query("SELECT r FROM Room r JOIN r.cinema c JOIN c.address a WHERE a.city = :city")
    List<Room> findByCityInRooms(@Param("city") String city);
}