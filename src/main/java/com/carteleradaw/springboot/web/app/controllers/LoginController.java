package com.carteleradaw.springboot.web.app.controllers;

import com.carteleradaw.springboot.web.app.services.impl.GlobalStateServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Scope("session")
@Controller
public class LoginController {

    private final GlobalStateServiceImpl globalStateService;

    /**
     * Muestra la página de login.
     * @param model Modelo.
     * @return Plantilla login.
     */
    @RequestMapping("/login")
    public String login(Model model) {

        model.addAttribute("cities", globalStateService.getCitiesNames());
        model.addAttribute("selectedCity", globalStateService.getSelectedCity());
        model.addAttribute("returnUrl", "login");

        return "login";
    }

    /**
     * Muestra la páginan de logout.
     * @param request Sesión.
     * @param model Modelo.
     * @return Plantilla logout.
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {

        model.addAttribute("cities", globalStateService.getCitiesNames());
        model.addAttribute("selectedCity", globalStateService.getSelectedCity());
        model.addAttribute("returnUrl", "/");

        HttpSession session = request.getSession(false);
        if (session!= null) {
            session.invalidate();
        }
        return "logout";
    }
}
