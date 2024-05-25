package com.carteleradaw.springboot.web.app.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cif;

    @Column(unique = true, nullable = false)
    private String name;

    private String image;

    @Column(unique = true)
    private String url;

    @Column(unique = true)
    private String twitter;

    @Column(unique = true)
    private String linkedIn;

    @Column(unique = true)
    private String facebook;

    @Column(unique = true)
    private String instagram;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    // El mío
    @OneToOne
    @JoinColumn(unique = true, name = "address_id")
    private Address address;

//    @OneToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "address_id", nullable = false)
//    private Address address;

//    @OneToOne(optional = false, orphanRemoval = true)
//    @JoinColumn(unique = true, name = "address_id", nullable = false)
//    private Address address;

//    @OneToOne(optional = false, orphanRemoval = true, cascade = CascadeType.PERSIST)
//    @JoinColumn(unique = true, name = "address_id", nullable = false)
//    private Address address;

    @OneToMany(mappedBy = "cinema", fetch = FetchType.EAGER)
    private Set<Room> rooms = new HashSet<>();

    @Override
    public String toString() {
        return name + " - " + cif;
    }
}