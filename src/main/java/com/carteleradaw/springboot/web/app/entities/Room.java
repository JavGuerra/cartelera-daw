package com.carteleradaw.springboot.web.app.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static com.carteleradaw.springboot.web.app.utils.Utils.FormatDate;

/**
 * Entidad para salas.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(indexes = {
        @Index(name = "PK_FilmID", columnList = "film_id"),
        @Index(name = "PK_CinemaID", columnList = "cinema_id")
})
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1, message = "{Room.roomnumber.min}")
    @Digits(integer = 2, fraction = 0, message = "{Room.roomnumber.digits}")
    private Byte roomNumber; // Único, pero para cada cine.

    @Min(value = 1, message = "{Room.capacity.min}")
    @Digits(integer = 4, fraction = 0, message = "{Room.capacity.digits}")
    private Integer capacity;

    @Column(nullable = false)
    private Boolean active;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate premiere;

    @NotEmpty(message = "{Room.schedules.notempty}")
    @ElementCollection
    @ToString.Exclude
    private List<LocalTime> schedules = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @NotNull(message = "{Room.cinema.notnull}")
    @ManyToOne
    @JoinColumn(name = "cinema_id", nullable = false)
    private Cinema cinema;

    public Long getFilmId() {
        return this.film.getId();
    }

    public String getPremiereFormated() {
        return FormatDate(this.premiere);
    }

    public String getCity() {
        return this.cinema.getAddress().getCity();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Room room = (Room) o;
        return id != null && Objects.equals(id, room.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() { return "sala " + roomNumber; }
}