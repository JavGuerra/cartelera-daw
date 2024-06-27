package com.carteleradaw.springboot.web.app.services.impl;

import com.carteleradaw.springboot.web.app.services.IAddressService;
import com.carteleradaw.springboot.web.app.services.IGlobalStateService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Implementación de servicios de sesiones.
 */
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Slf4j
@AllArgsConstructor
@Service
public class GlobalStateServiceImpl implements IGlobalStateService {

    private final HttpSession session;
    private final IAddressService addressService;

    @PostConstruct
    public void init() {
        // Invalida la sesión existente para asegurar una nueva sesión
        session.invalidate();

        // Inicializa el atributo "selectedCity" como una cadena vacía si no existe
        if (session.getAttribute("selectedCity") == null) {
            session.setAttribute("selectedCity", "");
        }

        // Obtiene los nombres de las ciudades del servicio IAddressService
        Set<String> citiesNames = addressService.getCitiesNames();

        // Inicializa el conjunto de nombres de ciudades como un Set vacío si no existe
        if (session.getAttribute("citiesNames") == null) {
            session.setAttribute("citiesNames", citiesNames);
        } else {
            // Actualiza el conjunto de nombres de ciudades si ya existe
            session.setAttribute("citiesNames", citiesNames);
        }
    }

    @Override
    public String getSelectedCity() {
        log.info("getSelectedCity");
        return (String) session.getAttribute("selectedCity");
    }

    @Override
    public void setSelectedCity(String city) {
        log.info("setSelectedCity {}", city);
        session.setAttribute("selectedCity", city);
    }

    @Override
    public Set<String> getCitiesNames() {
        log.info("getCitiesNames");
        return (Set<String>) session.getAttribute("citiesNames");
    }

    @Override
    public void updateCitiesNames() {
        log.info("updateCitiesNames");
        Set<String> newCitiesNames = addressService.getCitiesNames();
        session.setAttribute("citiesNames", newCitiesNames);
    }

    // Método auxiliar para obtener HttpSession, si es necesario
    private HttpSession getSession() {
        return session;
    }
}
