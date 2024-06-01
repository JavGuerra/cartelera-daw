package com.carteleradaw.springboot.web.app.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Address {

    private final String postalCodeRegExp = "^(\\d{5})?$";
    private final String urlRegExp = "^((https?|ftp)://(www\\.)?[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+(:[0-9]+)?(/.*)?)?$";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street; // ( calle, número, escalera, piso )

    @Pattern(regexp = postalCodeRegExp, message = "Debe ingresar exactamente 5 dígitos o dejar el campo vacío.")
    @Column(length = 5)
    private String postalCode;

    @NotEmpty(message = "Debe ingresar una ciudad.")
    private String city;

    private String country;

    @Pattern(regexp = urlRegExp, message = "Formato de URL no válido.")
    private String map;

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