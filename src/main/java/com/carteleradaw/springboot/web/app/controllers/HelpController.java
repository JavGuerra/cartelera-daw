package com.carteleradaw.springboot.web.app.controllers;

import com.carteleradaw.springboot.web.app.services.GlobalStateService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Scope("session")
@Controller
public class HelpController {

    private final GlobalStateService globalStateService;

    /**
     * Crea la ruta para la página de ayuda.
     * @return página de ayuda.
     */
    @GetMapping("/help")
    public String showHelp(Model model) {
        model.addAttribute("cities", globalStateService.getCitiesNames());
        model.addAttribute("selectedCity", globalStateService.getSelectedCity());
        model.addAttribute("returnUrl", "help");
        return "help";
    }
}
