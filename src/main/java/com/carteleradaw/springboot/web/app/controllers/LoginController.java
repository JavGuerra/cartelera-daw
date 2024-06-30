package com.carteleradaw.springboot.web.app.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import static com.carteleradaw.springboot.web.app.utils.Utils.logoutAuth;

@Controller
public class LoginController {

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

        model.addAttribute("returnUrl", "/");

        if (session!= null) logoutAuth();
        return "logout";
    }
}
