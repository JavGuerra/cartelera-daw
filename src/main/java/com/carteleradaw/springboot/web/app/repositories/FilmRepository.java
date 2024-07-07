package com.carteleradaw.springboot.web.app.repositories;

import com.carteleradaw.springboot.web.app.entities.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repositorio de películas.
 */
public interface FilmRepository extends JpaRepository<Film, Long> {

    // @Query("SELECT f FROM Film f WHERE f.active = true")
    List<Film> findAllByActiveIsTrue();

    // @Query("SELECT f FROM Film f WHERE f.active = true")
    Page<Film> findAllByActiveIsTrue(Pageable paging);

    //@Query("SELECT f FROM Film f JOIN f.rooms r JOIN r.cinema c JOIN c.address a WHERE upper(a.city) = upper(:city)")
    Page<Film> findAllByRoomsCinemaAddressCityIgnoreCase(@Param("city") String city, Pageable paging);

    //@Query("SELECT f FROM Film f JOIN f.rooms r JOIN r.cinema c JOIN c.address a WHERE upper(a.city) = upper(:city) AND f.active = true")
    Page<Film> findAllByRoomsCinemaAddressCityIgnoreCaseAndActiveIsTrue(@Param("city") String city, Pageable paging);
}