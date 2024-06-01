package com.carteleradaw.springboot.web.app.entities;

import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
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

    private final String cifRegExp = "^[a-zA-Z][0-9]{7}[a-zA-Z]$";
    private final String urlRegExp = "^((https?|ftp)://(www\\.)?[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+(:[0-9]+)?(/.*)?)?$";
    private final String phoneRegExp ="^((\\+[0-9]{1,3}(\\s)?)?[0-9]{1,14})?$";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = cifRegExp, message = "Debe ingresar un CIF válido (a1234567Z).")
    @Column(unique = true, nullable = false)
    private String cif;

    @NotEmpty(message = "Debe ingresar un nombre.")
    @Column(unique = true, nullable = false)
    private String name;

    @Pattern(regexp = urlRegExp, message = "Formato de URL no válido.")
    private String image;

    @Pattern(regexp = urlRegExp, message = "Formato de URL no válido.")
    private String url;

    @Pattern(regexp = urlRegExp, message = "Formato de URL no válido.")
    private String twitter;

    @Pattern(regexp = urlRegExp, message = "Formato de URL no válido.")
    private String linkedIn;

    @Pattern(regexp = urlRegExp, message = "Formato de URL no válido.")
    private String facebook;

    @Pattern(regexp = urlRegExp, message = "Formato de URL no válido.")
    private String instagram;

    @Email(message = "Formato de Email no válido.")
    private String email;

    @Pattern(regexp = phoneRegExp, message = "Formato de teléfono internacional no válido.")
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