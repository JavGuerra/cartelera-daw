package com.carteleradaw.springboot.web.app.entities;

import com.carteleradaw.springboot.web.app.entities.enums.Classification;
import com.carteleradaw.springboot.web.app.entities.enums.Genre;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

import static com.carteleradaw.springboot.web.app.utils.Utils.URL_PATTERN;

/**
 * Entidad para películas.
 */
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

    @NotBlank(message = "{Film.title.notblank}")
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Boolean active;

    @Min(value = 1, message = "{Film.duration.min}")
    @Digits(integer = 5, fraction = 0, message = "{Film.duration.digits}")
    private Integer duration;

    @Min(value = 1900, message = "{Film.year.min}")
    @Digits(integer = 4, fraction = 0, message = "{Film.year.digits}")
    @Column(nullable = false)
    private Integer year;

    private String director;

    private String country;

    private Classification classification;

    @Min(value = 1, message = "{Film.rating.min}")
    @Max(value = 5, message = "{Film.rating.max}")
    @Digits(integer = 1, fraction = 0, message = "{Film.rating.digits}")
    private Byte rating;

    private String music;

    private String photography;

    private String script;

    private String company;

    private String actor;

    @ElementCollection
    private Set<Genre> genres = new HashSet<>();

    @Pattern(regexp = URL_PATTERN, message = "{Film.trailer.pattern}")
    private String trailer;

    @Pattern(regexp = URL_PATTERN, message = "{Film.poster.pattern}")
    private String poster;

    @NotBlank(message = "{Film.synopsis.notblank}")
    @Column(nullable = false)
    private String synopsis;

    @Pattern(regexp = URL_PATTERN, message = "{Film.review.pattern}")
    private String review;

    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY)
    private Set<Room> rooms = new HashSet<>();

    @Override
    public String toString() {
        return "«" + title + "» (" + year + ")";
    }
}