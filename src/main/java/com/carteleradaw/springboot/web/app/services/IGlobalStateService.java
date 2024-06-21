package com.carteleradaw.springboot.web.app.services;

import java.util.Set;

/**
 * Interfaz para servicios de sesión.
 */
public interface IGlobalStateService {

    /**
     * obtiene la ciudad seleccionada.
     * @return ciudad seleccionada.
     */
    String getSelectedCity();

    /**
     * Establece la ciudad seleccionada.
     * @param city ciudad seleccionada.
     */
    void setSelectedCity(String city);

    /**
     * Obtiene la lista de ciudades disponibles.
     * @return Lista de ciudades únicas.
     */
    Set<String> getCitiesNames();

    /**
     * Actualiza los nombres de las ciudades en la lista de ciudades.
     */
    void updateCitiesNames();
}
