package com.carteleradaw.springboot.web.app.controllers;

import com.carteleradaw.springboot.web.app.services.impl.GlobalStateServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Scope("session")
@Controller
public class LegalController {

    private final GlobalStateServiceImpl globalStateService;

    /**
     * Crea la ruta para la página de aviso legal.
     * @return página del aviso legal.
     */
    @GetMapping("/legal")
    public String showLegal(Model model) {
        model.addAttribute("cities", globalStateService.getCitiesNames());
        model.addAttribute("selectedCity", globalStateService.getSelectedCity());
        model.addAttribute("returnUrl", "legal");
        return "legal";
    }

    /**
     * Crea la ruta para la página de política de privacidad.
     * @return página de la política de privacidad.
     */
    @GetMapping("/privacity")
    public String showPrivacity(Model model) {
        model.addAttribute("cities", globalStateService.getCitiesNames());
        model.addAttribute("selectedCity", globalStateService.getSelectedCity());
        model.addAttribute("returnUrl", "privacity");
        return "privacity";
    }
}
