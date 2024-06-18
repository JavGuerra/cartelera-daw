package com.carteleradaw.springboot.web.app.repositories;

import com.carteleradaw.springboot.web.app.entities.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT r FROM Room r WHERE r.active = true")
    Page<Room> findAllByActiveTrue(Pageable paging);

    @Query("SELECT r FROM Room r WHERE r.cinema.id = :id")
    List<Room> findAllByCinema_Id(@Param("id") Long id);

    @Query("SELECT r FROM Room r WHERE r.cinema.id = :id")
    Page<Room> findAllByCinema_Id(@Param("id") Long id, Pageable paging);

    @Query("SELECT r FROM Room r WHERE r.cinema.id = :id AND r.active = true")
    Page<Room> findAllByCinema_IdAndActiveTrue(@Param("id") Long id, Pageable paging);

    @Query("SELECT r FROM Room r WHERE r.film.id = :id")
    List<Room> findAllByFilm_Id(@Param("id") Long id);

    @Query("SELECT r FROM Room r WHERE r.film.id = :id")
    Page<Room> findAllByFilm_Id(@Param("id") Long id, Pageable paging);

    @Query("SELECT r FROM Room r WHERE r.film.id = :id AND r.active = true")
    Page<Room> findAllByFilm_IdAndActiveTrue(@Param("id") Long id, Pageable paging);

    @Query("SELECT r FROM Room r WHERE r.film.id = :id AND upper(r.cinema.address.city) = upper(:city)")
    Page<Room> findAllByFilm_IdAndCity(@Param("id") Long id, @Param("city") String city, Pageable paging);

    @Query("SELECT r FROM Room r WHERE r.film.id = :id AND upper(r.cinema.address.city) = upper(:city) AND r.active = true")
    Page<Room> findAllByFilm_IdAndCityAndActiveTrue(@Param("id") Long filmId, @Param("city") String city, Pageable paging);

    @Query("SELECT r FROM Room r ORDER BY r.premiere DESC")
    List<Room> findAllByPremiereDesc();

    @Query("SELECT r FROM Room r WHERE r.active = true ORDER BY r.premiere DESC")
    List<Room> findAllByPremiereDescAndActiveTrue();

    @Query("SELECT r FROM Room r WHERE upper(r.cinema.address.city) = upper(:city) ORDER BY r.premiere DESC")
    List<Room> findAllByCityAndPremiereDesc(@Param("city") String city);

    @Query("SELECT r FROM Room r WHERE upper(r.cinema.address.city) = upper(:city) AND r.active = true ORDER BY r.premiere DESC")
    List<Room> findAllByCityAndPremiereDescAndActiveTrue(@Param("city") String city);

    @Query("SELECT r FROM Room r JOIN r.cinema c JOIN c.address a WHERE upper(a.city) = upper(:city)")
    Page<Room> findAllByCityInRoom(@Param("city") String city, Pageable pageable);

    @Query("SELECT r FROM Room r JOIN r.cinema c JOIN c.address a WHERE upper(a.city) = upper(:city) AND r.active = true")
    Page<Room> findAllByCityInRoomAndActiveTrue(@Param("city") String city, Pageable pageable);

    @Query("SELECT r FROM Room r WHERE r.cinema.id = :id AND r.active = true")
    List<Room> findAllByCinema_IdAndActiveTrue(@Param("id") Long id);

    @Query("SELECT r FROM Room r WHERE r.cinema.id = :id AND r.roomNumber = :roomNumber AND r.active = true")
    Optional<Room> findRoomByCinemaIdAndRoomNumberAndActiveTrue(@Param("id") Long cinemaId, @Param("roomNumber") Byte roomNumber);

}