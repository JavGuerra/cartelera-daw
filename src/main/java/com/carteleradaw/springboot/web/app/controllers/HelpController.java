package com.carteleradaw.springboot.web.app.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador de ruta para la ayuda.
 */
@RequiredArgsConstructor
@Controller
public class HelpController {

    /**
     * Crea la ruta para la página de ayuda.
     * @return página de ayuda.
     */
    @GetMapping("/help")
    public String showHelp(Model model) {
        model.addAttribute("returnUrl", "help");
        return "help";
    }
}
