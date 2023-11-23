package cartelera.services;

import cartelera.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    /**
     * Obtiene la lista completa de usuarios.
     * @return Lista de usuarios.
     */
    List<User> findAll();

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
