package com.carteleradaw.springboot.web.app.repositories;

import com.carteleradaw.springboot.web.app.entities.Cinema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio de cines.
 */
public interface CinemaRepository extends JpaRepository<Cinema, Long> {

    @Query("SELECT c from Cinema c WHERE c.address.id = :id")
    Cinema findByAddress_Id(@Param("id") Long id);

    @Query("SELECT c from Cinema c WHERE c.active = true")
    Page<Cinema> findAllByActiveTrue(Pageable paging);

    @Query("SELECT c from Cinema c WHERE upper(c.cif) = upper(:cif)")
    Optional<Cinema> findByCifIgnoreCase(@Param("cif") String cif);

    @Query("SELECT c FROM Cinema c JOIN c.address a WHERE upper(a.city) = upper(:city)")
    Page<Cinema> findAllByCityIgnoreCase(@Param("city") String city, Pageable paging);

    @Query("SELECT c FROM Cinema c WHERE c.active = true")
    List<Cinema> findAllByCinemaAndActiveTrue();

    @Query("SELECT c FROM Cinema c JOIN c.address a WHERE upper(a.city) = upper(:city) AND c.active = true")
    Page<Cinema> findAllByCityIgnoreCaseAndActiveTrue(@Param("city") String city, Pageable paging);
}