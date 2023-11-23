package com.carteleradaw.springboot.web.app.repositories;

import com.carteleradaw.springboot.web.app.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {

    // Películas de un cine
    //@Query("select f from Film f inner join f.rooms rooms where rooms.cinema.id = ?1")
    // List<Film> findAllByRooms_Cinema_Id(Long id);

    // Películas de una ciudad
    // @Query("select f from Film f inner join f.rooms rooms where upper(rooms.cinema.address.city) = upper(?1)")
    // List<Film> findAllByRooms_Cinema_Address_CityIgnoreCase(String city);

    // List<Film> findAllByGenders(String gender);
}