package com.carteleradaw.springboot.web.app.entities;

import jakarta.persistence.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

import static com.carteleradaw.springboot.web.app.utils.Utils.*;

/**
 * Entidad para cines.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(indexes = {@Index(name = "PK_AddressID", columnList = "address_id", unique = true)})
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = CIF_PATTERN, message = "{Cinema.cif.pattern}")
    @Size(max = 9, message = "{Cinema.cif.size}")
    @Column(nullable = false, length = 9)
    private String cif;

    @Column(nullable = false)
    private Boolean active;

    @NotBlank(message = "{Cinema.name.notblank}")
    @Column(nullable = false)
    private String name;

    @Pattern(regexp = URL_PATTERN, message = "{Cinema.image.pattern}")
    private String image;

    @Pattern(regexp = URL_PATTERN, message = "{Cinema.url.pattern}")
    private String url;

    @Pattern(regexp = URL_PATTERN, message = "{Cinema.twitter.pattern}")
    private String twitter;

    @Pattern(regexp = URL_PATTERN, message = "{Cinema.linkedin.pattern}")
    private String linkedIn;

    @Pattern(regexp = URL_PATTERN, message = "{Cinema.facebook.pattern}")
    private String facebook;

    @Pattern(regexp = URL_PATTERN, message = "{Cinema.instagram.pattern}")
    private String instagram;

    @Email(message = "{Cinema.email.email}")
    private String email;

    @Pattern(regexp = PHONE_PATTERN, message = "{Cinema.phone.pattern}")
    private String phone;

    @NotNull(message = "{Cinema.address.notnull}")
    @OneToOne(optional = false, orphanRemoval = true, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id", unique = true)
    @Valid // Importante para poder validar los campos de la entidad Address asociada en el formulario.
    private Address address;

    @OneToMany(mappedBy = "cinema", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Room> rooms = new HashSet<>();

    public Integer getCountRooms() {
        if (isAuth()) return rooms.size();
        return (int) rooms.stream().filter(Room::getActive).count();
    }

    @Override
    public String toString() {
        return name + " - " + cif;
    }
}