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

    private String url;

    private String twitter;

    private String linkedIn;

    private String facebook;

    private String instagram;

    private String email;

    private String phone;

    @OneToOne(optional = false, orphanRemoval = true, cascade = CascadeType.PERSIST)
    @JoinColumn(unique = true, name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "cinema", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Room> rooms = new HashSet<>();

    @Override
    public String toString() {
        return name + " - " + cif;
    }
}