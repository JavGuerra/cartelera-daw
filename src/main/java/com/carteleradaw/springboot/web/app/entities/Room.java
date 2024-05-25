package com.carteleradaw.springboot.web.app.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    private Byte roomNumber; // Único, pero para cada cine.

    private Integer capacity;

    private Boolean active;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate premiere;

    @ElementCollection
    @ToString.Exclude
    private Set<LocalTime> schedules = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
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