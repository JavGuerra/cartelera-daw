package com.carteleradaw.springboot.web.app.entities;

import com.carteleradaw.springboot.web.app.entities.enums.Classification;
import com.carteleradaw.springboot.web.app.entities.enums.Gender;
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
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    private Integer duration;

    @Column(nullable = false)
    private Integer year;

    private String director;

    private String country;

    private Classification classification;

    private Byte rating;

    private String music;

    private  String photography;

    private String script;

    private String company;

    private String actor;

    @ElementCollection
    private Set<Gender> genders = new HashSet<>();

    private String trailer;

    private String poster;

    private String synopsis;

    @OneToMany(mappedBy = "film", fetch = FetchType.EAGER)
    private Set<Room> rooms = new HashSet<>();

    @Override
    public String toString() {
        return "«" + title + "» (" + year + ')';
    }
}