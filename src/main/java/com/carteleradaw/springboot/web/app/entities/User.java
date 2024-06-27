package com.carteleradaw.springboot.web.app.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import static com.carteleradaw.springboot.web.app.utils.Utils.URL_PATTERN;

/**
 * Entidad para usuarios.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "users", indexes = {
        @Index(name = "UK_UserName", columnList = "username", unique = true),
        @Index(name = "UK_Email", columnList = "email", unique = true)
})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = URL_PATTERN, message = "Formato de URL no válido.")
    private String image;

    @NotBlank(message = "Debe ingresar un nombre.")
    private String name;

    @NotBlank(message = "Debe ingresar los apellidos.")
    private String surname;

    @NotBlank(message = "Debe ingresar un nombre de usuario.")
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank(message = "Debe ingresar un correo.")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Debe ingresar una contraseña.")
    @Column(nullable = false)
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("READ");
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
