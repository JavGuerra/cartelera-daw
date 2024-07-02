package com.carteleradaw.springboot.web.app.config;

import com.carteleradaw.springboot.web.app.services.IAddressService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 *  Manejador para el cierre de sesión.
 */
@AllArgsConstructor
@Component
public class CustomLogoutHandler implements LogoutHandler {

    private final IAddressService addressService;

    // TODO No funciona. Operativa movida a controlador "/logout" en LoginController.

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        try {

            System.out.println("********************************************************************************");
            System.out.println("Se está cerrando sesión del usuario: " + authentication.getName());

            SecurityContextHolder.getContext().setAuthentication(null);

            HttpSession session = request.getSession(false); // Usa false para no crear una nueva sesión si no es necesario
            if (session!= null) {
                Set<String> citiesNames = addressService.getCitiesNames();
                String selectedCity = (String) session.getAttribute("selectedCity");

                session.setAttribute("citiesNames", citiesNames);
                if (!citiesNames.contains(selectedCity)) session.setAttribute("selectedCity", "");
            }
        } catch (Exception e) {
            // Log the error or handle it appropriately
            System.err.println("Error during logout: " + e.getMessage());
        }
    }
}
