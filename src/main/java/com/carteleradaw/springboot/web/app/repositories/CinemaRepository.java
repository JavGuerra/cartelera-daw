package com.carteleradaw.springboot.web.app.repositories;

import com.carteleradaw.springboot.web.app.entities.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {

    @Query("select c from Cinema c where c.address.id = ?1")
    Cinema findByAddress_Id(Long id);

    @Query("SELECT c FROM Cinema c JOIN c.address a WHERE a.city = :city")
    List<Cinema> findByCity(@Param("city") String city);
}