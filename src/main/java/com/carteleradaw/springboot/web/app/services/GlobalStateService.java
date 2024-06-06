package com.carteleradaw.springboot.web.app.services;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class GlobalStateService {

    private final HttpSession session;
    private final IAddressService addressService;

    // Suponiendo que HttpSession se inyecta directamente
    public GlobalStateService(HttpSession session, IAddressService addressService) {
        this.session = session;
        this.addressService = addressService;
    }

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

    public String getSelectedCity() {
        return (String) session.getAttribute("selectedCity");
    }

    public void setSelectedCity(String city) {
        session.setAttribute("selectedCity", city);
    }

    public Set<String> getCitiesNames() {
        return (Set<String>) session.getAttribute("citiesNames");
    }

    public void updateCitiesNames() {
        Set<String> newCitiesNames = addressService.getCitiesNames();
        session.setAttribute("citiesNames", newCitiesNames);
    }

    // Método auxiliar para obtener HttpSession, si es necesario
    private HttpSession getSession() {
        return session;
    }
}
