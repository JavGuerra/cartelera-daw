package com.carteleradaw.springboot.web.app.controllers;

import com.carteleradaw.springboot.web.app.services.IAddressService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@RequiredArgsConstructor
@Controller
public class LoginController {

    private final IAddressService addressService;

    /**
     * Muestra la página de login.
     * @param model Modelo.
     * @return Plantilla login.
     */
    @RequestMapping("/login")
    public String login(Model model) {

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

        SecurityContextHolder.getContext().setAuthentication(null);

        model.addAttribute("returnUrl", "/");

        Set<String> citiesNames = addressService.getCitiesNames();
        String selectedCity = (String) session.getAttribute("selectedCity");
        session.setAttribute("citiesNames", citiesNames);
        if (!citiesNames.contains(selectedCity)) session.setAttribute("selectedCity", "");

        return "logout";
    }
}
