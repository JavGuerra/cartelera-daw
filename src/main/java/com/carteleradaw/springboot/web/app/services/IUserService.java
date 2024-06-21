package com.carteleradaw.springboot.web.app.services;

import com.carteleradaw.springboot.web.app.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Interfaz para servicios de usuarios.
 */
public interface IUserService {

    /**
     * Obtiene la lista completa de usuarios.
     * @return Lista de usuarios.
     */
    Page<User> findAll(Pageable paging);

    /**
     * Comprueba si existe un usuario por si ID.
     * @param id Identificador.
     * @return Verdadero si existe, falso en caso contrario.
     */
    boolean existsById(Long id);

    /**
     * Obtiene un usuario por su ID.
     * @param id Identificador.
     * @return Opcionalmente, el usuario solicitado.
     */
    Optional<User> findById(Long id);

    /**
     * Comprueba si existe un usuario por el nombre de usuario.
     * @param username nombre de usuario.
     * @return Verdadero si existe, falso en caso contrario.
     */
    boolean existsByUsername(String username);

    /**
     * Obtiene un usuario por su nombre de usuario.
     * @param username nombre de usuario.
     * @return Opcionalmente, el usuario solicitado.
     */
    Optional<User> findByUsername(String username);

    /**
     * Comprueba si existe un usuario por su correo electrónico.
     * @param email correo electrónico.
     * @return Verdadero si existe, falso en caso contrario.
     */
    boolean existsByEmail(String email);

    /**
     * Obtiene un usuario por su correo electrónico.
     * @param email correo electrónico.
     * @return Opcionalmente, el usuario solicitado.
     */
    Optional<User> findByEmail(String email);

    /**
     * Guarda un usuario.
     * @param user Usuario.
     * @return El usuario guardado.
     */
    User save(User user);

    /**
     * Birra una dirección por el ID.
     * @param id Identificador.
     */
    void deleteById(Long id);
}
