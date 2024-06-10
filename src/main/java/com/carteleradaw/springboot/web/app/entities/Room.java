package com.carteleradaw.springboot.web.app.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static com.carteleradaw.springboot.web.app.utils.Utils.FormatDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1, message = "El valor debe ser mayor o igual a 1")
    @Digits(integer = 2, fraction = 0, message = "Solo se permiten números enteros")
    private Byte roomNumber; // Único, pero para cada cine.

    @Min(value = 1, message = "El valor debe ser mayor o igual a 1")
    @Digits(integer = 4, fraction = 0, message = "Solo se permiten números enteros")
    private Integer capacity;

    @Column(nullable = false)
    private Boolean active;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate premiere;

    @ElementCollection
    @ToString.Exclude
    private List<LocalTime> schedules = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @NotNull(message = "El campo cine no puede estar vacío.")
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
}