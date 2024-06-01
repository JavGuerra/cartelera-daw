package com.carteleradaw.springboot.web.app.entities;

import com.carteleradaw.springboot.web.app.entities.enums.Classification;
import com.carteleradaw.springboot.web.app.entities.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    private final String urlRegExp = "^((https?|ftp)://(www\\.)?[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+(:[0-9]+)?(/.*)?)?$";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Debe ingresar un título.")
    @Column(unique = true, nullable = false)
    private String title;

    @Min(value = 1, message = "El valor debe ser mayor o igual a 1")
    @Digits(integer = 5, fraction = 0, message = "Solo se permiten números enteros")
    private Integer duration;

    @Min(value = 1900, message = "El valor debe ser mayor o igual a 1900")
    @Digits(integer = 4, fraction = 0, message = "Debe ingresar un año (2024)")
    @Column(nullable = false)
    private Integer year;

    private String director;

    private String country;

    private Classification classification;

    @Min(value = 1, message = "El valor debe estar entre 1 y 5.")
    @Max(value = 5, message = "El valor debe estar entre 1 y 5.")
    @Digits(integer = 1, fraction = 0, message = "Solo se permiten números enteros")
    private Byte rating;

    private String music;

    private String photography;

    private String script;

    private String company;

    private String actor;

    @ElementCollection
    private Set<Gender> genders = new HashSet<>();

    @Pattern(regexp = urlRegExp, message = "Formato de URL no válido.")
    private String trailer;

    @Pattern(regexp = urlRegExp, message = "Formato de URL no válido.")
    private String poster;

    private String synopsis;

    @OneToMany(mappedBy = "film", fetch = FetchType.EAGER)
    private Set<Room> rooms = new HashSet<>();

    @Override
    public String toString() {
        return "«" + title + "» (" + year + ")";
    }
}