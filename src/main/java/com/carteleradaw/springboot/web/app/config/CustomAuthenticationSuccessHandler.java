package com.carteleradaw.springboot.web.app.config;

import com.carteleradaw.springboot.web.app.services.IAddressService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Manejador para el inicio de sesión.
 */
@Slf4j
@AllArgsConstructor
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final IAddressService addressService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        HttpSession session = request.getSession(false);

        if (authentication.isAuthenticated()) {
            log.info("onAuthenticationSuccess {}", authentication.getName());
            session.setAttribute("citiesNames", addressService.getCitiesNames());
        }

        response.sendRedirect("/");
    }
}
