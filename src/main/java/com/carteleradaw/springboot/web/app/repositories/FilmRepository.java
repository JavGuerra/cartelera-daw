package com.carteleradaw.springboot.web.app.repositories;

import com.carteleradaw.springboot.web.app.entities.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repositorio de películas.
 */
public interface FilmRepository extends JpaRepository<Film, Long> {

    @Query("SELECT f FROM Film f WHERE f.active = true")
    List<Film> findAllByActiveTrue();

    @Query("SELECT f FROM Film f WHERE f.active = true")
    Page<Film> findAllByActiveTrue(Pageable paging);

    @Query("SELECT f FROM Film f JOIN f.rooms r JOIN r.cinema c JOIN c.address a WHERE upper(a.city) = upper(:city)")
    Page<Film> findAllByCityInFilms(@Param("city") String city, Pageable paging);

    @Query("SELECT f FROM Film f JOIN f.rooms r JOIN r.cinema c JOIN c.address a WHERE upper(a.city) = upper(:city) AND f.active = true")
    Page<Film> findAllByCityInFilmsAndActiveTrue(@Param("city") String cit, Pageable pagingy);

//    @Query("SELECT f FROM Film f JOIN f.genres g WHERE g.name = :genre")
//    List<Film> findAllByGenreInFilms(@Param("genre") String genre);
}