package com.carteleradaw.springboot.web.app.controllers;

import com.carteleradaw.springboot.web.app.services.GlobalStateService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@AllArgsConstructor
@Scope("session")
@Controller
public class LegalController {

    private final GlobalStateService globalStateService;

    /**
     * Crea la ruta para la página de aviso legal.
     * @return página del aviso legal.
     */
    @GetMapping("/legal")
    public String showLegal(Model model) {
        Set<String> citiesNames = globalStateService.getCitiesNames();
        String selectedCity = globalStateService.getSelectedCity();
        model.addAttribute("cities", citiesNames);
        model.addAttribute("selectedCity", selectedCity);
        model.addAttribute("returnUrl", "legal");
        return "legal";
    }

    /**
     * Crea la ruta para la página de política de privacidad.
     * @return página de la política de privacidad.
     */
    @GetMapping("/privacity")
    public String showPrivacity(Model model) {
        Set<String> citiesNames = globalStateService.getCitiesNames();
        String selectedCity = globalStateService.getSelectedCity();
        model.addAttribute("cities", citiesNames);
        model.addAttribute("selectedCity", selectedCity);
        model.addAttribute("returnUrl", "privacity");
        return "privacity";
    }
}
