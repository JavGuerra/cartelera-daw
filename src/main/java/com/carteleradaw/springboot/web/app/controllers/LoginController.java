package com.carteleradaw.springboot.web.app.controllers;

import com.carteleradaw.springboot.web.app.services.IAddressService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Controller
public class LoginController {

    private final AnonymousAuthenticationToken anonymousAuthenticationToken;
    private final IAddressService addressService;

    /**
     * Muestra la página de login.
     * @param model Modelo.
     * @return Plantilla login.
     */
    @RequestMapping("/login")
    public String login(Model model) {
        log.info("login");

        model.addAttribute("returnUrl", "login");

        return "login";
    }

    /**
     * Muestra la páginan de logout.
     * @param session Sesión.
     * @param model Modelo.
     * @return Plantilla logout.
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session, Model model) {
        log.info("logout");

        // SecurityContextHolder.getContext().setAuthentication(null);
        // Si se usa null para logout, al mostrar /logout, sec:authorize en el navbar no equivale ni a "isAnonymous()"
        // ni a "isAuthenticated()", por lo que hay opciones del menú que no se muestran. La alternativa es usar un Bean
        // propio «anonymousAuthenticationToken» definido en SecurityConfig para cambiar la autorización del usuario:
        SecurityContextHolder.getContext().setAuthentication(anonymousAuthenticationToken);

        model.addAttribute("returnUrl", "/");

        Set<String> citiesNames = addressService.getCitiesNames();
        String selectedCity = (String) session.getAttribute("selectedCity");
        session.setAttribute("citiesNames", citiesNames);
        if (!citiesNames.contains(selectedCity)) session.setAttribute("selectedCity", "");

        session.setAttribute("message", "");

        return "logout";
    }
}
