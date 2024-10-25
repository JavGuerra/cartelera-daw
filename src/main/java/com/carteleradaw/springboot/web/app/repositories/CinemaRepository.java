package com.carteleradaw.springboot.web.app.repositories;

import com.carteleradaw.springboot.web.app.entities.Cinema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio de cines.
 */
public interface CinemaRepository extends JpaRepository<Cinema, Long> {

    // @Query("SELECT c FROM Cinema c WHERE c.address.id = :id")
    Cinema findByAddress_Id(@Param("id") Long addressId);

    // @Query("SELECT c FROM Cinema c WHERE upper(c.cif) = upper(:cif)")
    Optional<Cinema> findByCifIgnoreCase(@Param("cif") String cif);

    // @Query("SELECT * FROM Cinema WHERE active = true")
    List<Cinema> findAllByActiveIsTrue();

    // @Query("SELECT * FROM Cinema WHERE active = true")
    Page<Cinema> findAllByActiveIsTrue(Pageable paging);

    // @Query("SELECT c FROM Cinema c JOIN c.address a WHERE upper(a.city) = upper(:city)")
    Page<Cinema> findAllByAddressCityIgnoreCase(@Param("city") String city, Pageable paging);

    // @Query("SELECT c FROM Cinema c JOIN c.address a WHERE upper(a.city) = upper(:city) AND c.active = true")
    Page<Cinema> findAllByAddressCityIgnoreCaseAndActiveIsTrue(@Param("city") String city, Pageable paging);
}