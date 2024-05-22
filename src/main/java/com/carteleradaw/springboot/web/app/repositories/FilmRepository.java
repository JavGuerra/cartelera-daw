package com.carteleradaw.springboot.web.app.repositories;

import com.carteleradaw.springboot.web.app.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {

    @Query("SELECT f FROM Film f JOIN f.rooms r JOIN r.cinema c JOIN c.address a WHERE a.city = :city")
    List<Film> findByCityInFilms(@Param("city") String city);
}