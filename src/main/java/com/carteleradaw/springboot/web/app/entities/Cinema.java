package com.carteleradaw.springboot.web.app.entities;

import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

import static com.carteleradaw.springboot.web.app.utils.Utils.CIF_PATTERN;
import static com.carteleradaw.springboot.web.app.utils.Utils.URL_PATTERN;
import static com.carteleradaw.springboot.web.app.utils.Utils.PHONE_PATTERN;

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

    @Pattern(regexp = CIF_PATTERN, message = "Debe ingresar un CIF válido (a1234567Z).")
    @Column(unique = true, nullable = false)
    private String cif;

    private Boolean active;

    @NotEmpty(message = "Debe ingresar un nombre.")
    @Column(unique = true, nullable = false)
    private String name;

    @Pattern(regexp = URL_PATTERN, message = "Formato de URL no válido.")
    private String image;

    @Pattern(regexp = URL_PATTERN, message = "Formato de URL no válido.")
    private String url;

    @Pattern(regexp = URL_PATTERN, message = "Formato de URL no válido.")
    private String twitter;

    @Pattern(regexp = URL_PATTERN, message = "Formato de URL no válido.")
    private String linkedIn;

    @Pattern(regexp = URL_PATTERN, message = "Formato de URL no válido.")
    private String facebook;

    @Pattern(regexp = URL_PATTERN, message = "Formato de URL no válido.")
    private String instagram;

    @Email(message = "Formato de Email no válido.")
    private String email;

    @Pattern(regexp = PHONE_PATTERN, message = "Formato de teléfono internacional no válido.")
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