package com.carteleradaw.springboot.web.app.repositories;

import com.carteleradaw.springboot.web.app.entities.Address;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("select a from Address a where upper(a.city) = upper(?1)")
    List<Address> findByCityIgnoreCase(String city);

//    @EntityGraph(attributePaths = {"cinema"})
//    List<Address> findAllWithCinemaInfo();

    //@Query("SELECT a FROM Address a JOIN FETCH a.cinema WHERE a.id = :id")
    //Address findByIdWithCinemaInfo(@Param("id") Long id);
}