package com.carteleradaw.springboot.web.app.entities;

import com.carteleradaw.springboot.web.app.entities.enums.Classification;
import com.carteleradaw.springboot.web.app.entities.enums.Genre;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

import static com.carteleradaw.springboot.web.app.utils.Utils.URL_PATTERN;

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

    @NotBlank(message = "Debe ingresar un título.")
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Boolean active;

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
    private Set<Genre> genres = new HashSet<>();

    @Pattern(regexp = URL_PATTERN, message = "Formato de URL no válido.")
    private String trailer;

    @Pattern(regexp = URL_PATTERN, message = "Formato de URL no válido.")
    private String poster;

    @NotBlank(message = "Debe ingresar una sinopsis.")
    @Column(nullable = false)
    private String synopsis;

    @Pattern(regexp = URL_PATTERN, message = "Formato de URL no válido.")
    private String review;

    @OneToMany(mappedBy = "film", fetch = FetchType.EAGER)
    private Set<Room> rooms = new HashSet<>();

    @Override
    public String toString() {
        return "«" + title + "» (" + year + ")";
    }
}