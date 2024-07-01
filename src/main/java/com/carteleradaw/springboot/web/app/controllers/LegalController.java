package com.carteleradaw.springboot.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controladores de rutas para legal y privacidad.
 */
@Controller
public class LegalController {

    /**
     * Crea la ruta para la página de aviso legal.
     * @return página del aviso legal.
     */
    @GetMapping("/legal")
    public String showLegal(Model model) {
        model.addAttribute("returnUrl", "legal");
        return "legal";
    }

    /**
     * Crea la ruta para la página de política de privacidad.
     * @return página de la política de privacidad.
     */
    @GetMapping("/privacy")
    public String showPrivacy(Model model) {
        model.addAttribute("returnUrl", "privacy");
        return "privacy";
    }
}
