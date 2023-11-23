package com.carteleradaw.springboot.web.app.repositories;

import com.carteleradaw.springboot.web.app.entities.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {

    @Query("select c from Cinema c where c.address.id = ?1")
    Cinema findByAddress_Id(Long id);

    // List<Cinema> findAllByAddressCity(String city);
}