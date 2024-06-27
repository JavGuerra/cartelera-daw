package com.carteleradaw.springboot.web.app.repositories;

import com.carteleradaw.springboot.web.app.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repositorio de direcciones.
 */
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT a FROM Address a WHERE upper(a.city) = upper(:city)")
    List<Address> findAllByCityIgnoreCase(@Param("city") String city);

    @Query("SELECT c.address FROM Cinema c WHERE c.active = true")
    List<Address> findByCinemaActiveTrue();
}