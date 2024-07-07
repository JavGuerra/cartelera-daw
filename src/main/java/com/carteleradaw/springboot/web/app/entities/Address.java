package com.carteleradaw.springboot.web.app.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import static com.carteleradaw.springboot.web.app.utils.Utils.POSTAL_CODE_PATTERN;
import static com.carteleradaw.springboot.web.app.utils.Utils.URL_PATTERN;

/**
 * Entidad para direcciones.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street; // ( calle, número, escalera, piso )

    @Pattern(regexp = POSTAL_CODE_PATTERN, message = "{Address.postalcode.pattern}")
    @Column(length = 5)
    private String postalCode;

    @NotBlank(message = "{Address.city.notblank}")
    private String city;

    private String country;

    @Pattern(regexp = URL_PATTERN, message = "{Address.map.pattern}")
    private String map;

    @OneToOne(mappedBy = "address")
    private Cinema cinema;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (!street.isEmpty()) {
            sb.append(street);
            sb.append(", ");
        }

        if (!postalCode.isEmpty()) {
            sb.append(postalCode);
            sb.append(" - ");
        }

        sb.append(city);

        return sb.toString(); // Retorna la cadena construida
    }
}