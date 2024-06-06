package com.carteleradaw.springboot.web.app.repositories;

import com.carteleradaw.springboot.web.app.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT r FROM Room r WHERE r.cinema.id = :id")
    List<Room> findAllByCinema_Id(@Param("id") Long id);

    @Query("SELECT r FROM Room r WHERE r.film.id = :id")
    List<Room> findAllByFilm_Id(@Param("id") Long id);

    @Query("SELECT r FROM Room r WHERE r.active = true ORDER BY r.premiere DESC")
    List<Room> findAllActiveByPremiereDesc();

    @Query("SELECT r FROM Room r JOIN r.cinema c JOIN c.address a WHERE upper(a.city) = upper(:city)")
    List<Room> findByCityInRooms(@Param("city") String city);

    @Query("SELECT r FROM Room r WHERE r.cinema.id = :cinemaId AND r.roomNumber = :roomNumber AND r.active = true")
    Optional<Room> findRoomByCinemaIdAndRoomNumberAndActive(@Param("cinemaId") Long cinemaId, @Param("roomNumber") Byte roomNumber);

}