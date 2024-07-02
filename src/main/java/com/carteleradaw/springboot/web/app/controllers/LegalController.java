package com.carteleradaw.springboot.web.app.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.carteleradaw.springboot.web.app.utils.Utils.stringIsEmpty;

/**
 * Controladores de rutas para legal y privacidad.
 */
@Slf4j
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

    /**
     * Desactiva el aviso de cookies.
     * @param returnUrl Ruta a la que volver opcionalmente.
     * @param session Sesión HTTP.
     * @return Plantilla indicada o plantilla index.
     */
    @PostMapping("/cookie")
    public String closeCookiePolicy(@RequestParam(value = "returnUrl", required = false) String returnUrl,
                                   HttpSession session) {

        log.info("closeCookiePolicy");
        session.setAttribute("cookieWarning", false);

        if (!stringIsEmpty(returnUrl)) {
            return "redirect:" + returnUrl;
        } else {
            return "redirect:/";
        }
    }
}
