package com.carteleradaw.springboot.web.app.repositories;

import com.carteleradaw.springboot.web.app.entities.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio de salas.
 */
public interface RoomRepository extends JpaRepository<Room, Long> {

    // @Query("SELECT r FROM Room r WHERE r.active = true")
    Page<Room> findAllByActiveIsTrue(Pageable paging);

    // @Query("SELECT r FROM Room r WHERE r.cinema.id = :id")
    List<Room> findAllByCinema_Id(@Param("id") Long cinemaId);

    // @Query("SELECT r FROM Room r WHERE r.cinema.id = :id")
    Page<Room> findAllByCinema_Id(@Param("id") Long cinemaId, Pageable paging);

    // @Query("SELECT r FROM Room r WHERE r.cinema.id = :id AND r.active = true")
    Page<Room> findAllByCinema_IdAndActiveIsTrue(@Param("id") Long cinemaId, Pageable paging);

    // @Query("SELECT r FROM Room r WHERE r.film.id = :id")
    List<Room> findAllByFilm_Id(@Param("id") Long filmId);

    // @Query("SELECT r FROM Room r WHERE r.film.id = :id")
    Page<Room> findAllByFilm_Id(@Param("id") Long filmId, Pageable paging);

    // @Query("SELECT r FROM Room r WHERE r.film.id = :id AND r.active = true")
    Page<Room> findAllByFilm_IdAndActiveIsTrue(@Param("id") Long filmId, Pageable paging);

    // @Query("SELECT r FROM Room r WHERE r.film.id = :id AND upper(r.cinema.address.city) = upper(:city)")
    Page<Room> findAllByFilm_IdAndCinemaAddressCityIgnoreCase(@Param("id") Long filmId, @Param("city") String city, Pageable paging);

    // @Query("SELECT r FROM Room r WHERE r.film.id = :id AND upper(r.cinema.address.city) = upper(:city) AND r.active = true")
    Page<Room> findAllByFilm_IdAndCinemaAddressCityIgnoreCaseAndActiveIsTrue(@Param("id") Long filmId, @Param("city") String city, Pageable paging);

    // @Query("SELECT r FROM Room r WHERE r.active = true ORDER BY r.premiere DESC")
    List<Room> findAllByActiveIsTrueOrderByPremiereDesc();

    // @Query("SELECT r FROM Room r WHERE upper(r.cinema.address.city) = upper(:city) AND r.active = true ORDER BY r.premiere DESC")
    List<Room> findAllByCinemaAddressCityIgnoreCaseAndActiveIsTrueOrderByPremiereDesc(@Param("city") String city);

    // @Query("SELECT r FROM Room r JOIN r.cinema c JOIN c.address a WHERE upper(a.city) = upper(:city)")
    Page<Room> findAllByCinemaAddressCityIgnoreCase(@Param("city") String city, Pageable pageable);

    // @Query("SELECT r FROM Room r JOIN r.cinema c JOIN c.address a WHERE upper(a.city) = upper(:city) AND r.active = true")
    Page<Room> findAllByCinemaAddressCityIgnoreCaseAndActiveIsTrue(@Param("city") String city, Pageable pageable);

    // @Query("SELECT r FROM Room r WHERE r.cinema.id = :id AND r.active = true")
    List<Room> findAllByCinema_IdAndActiveIsTrue(@Param("id") Long cinemaId);

    // @Query("SELECT r FROM Room r WHERE r.cinema.id = :id AND r.roomNumber = :roomNumber AND r.active = true")
    Optional<Room> findByCinema_IdAndRoomNumberAndActiveIsTrue(@Param("id") Long cinemaId, @Param("roomNumber") Integer roomNumber);
}