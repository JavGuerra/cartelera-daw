package com.carteleradaw.springboot.web.app.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String street; // ( calle, número, escalera, piso )

    @Column(length = 6)
    private String postalCode;

    private String city;

    private String country;

    private String map;

    @Override
    public String toString() {
        return street + ", " + postalCode + " - " + city;
    }
}