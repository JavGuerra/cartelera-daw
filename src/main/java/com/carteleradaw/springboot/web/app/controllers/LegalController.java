package com.carteleradaw.springboot.web.app.controllers;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Scope("session")
@Controller
public class LegalController {

    /**
     * Crea la ruta para la página de aviso legal.
     * @return página del Aviso legal.
     */
    @GetMapping("/avisoLegal")
    public String showAvisoLegal() {
        return "avisoLegal";
    }

    /**
     * Crea la ruta para la página de política de privacidad.
     * @return página de la política de privacidad.
     */
    @GetMapping("/politicaPrivacidad")
    public String showPoliticaPrivacidad() {
        return "privacidad";
    }
}
