package cartelera.services;

import cartelera.entities.Film;

import java.util.List;
import java.util.Optional;

public interface IFilmService {

    /**
     * Obtiene una lista completa de películas.
     * @return Lista de películas.
     */
    List<Film> findAll();

    /**
     * Comprueba si existe una película por su ID.
     * @param id Identificador.
     * @return Verdadero si existe, falso en caso contrario.
     */
    boolean existsById(Long id);

    /**
     * Obtiene una película por si ID.
     * @param id Identificador.
     * @return Opcionalmente, la película seleccionada.
     */
    Optional<Film> findById(Long id);

    // List<Film> findAllByRoomsCinemaAddressCityIgnoreCase(String city);

    /**
     * Guarda una película.
     * @param film Película.
     * @return La película guardada.
     */
    Film save(Film film);

    /**
     *  borra una dirección por el ID.
     * @param id Identificador.
     */
    void deleteById(Long id);

    // List<Film> findAllByGenders(String gender);
}
