package com.carteleradaw.springboot.web.app.controllers;

import com.carteleradaw.springboot.web.app.entities.Room;
import com.carteleradaw.springboot.web.app.services.impl.GlobalStateServiceImpl;
import com.carteleradaw.springboot.web.app.services.IRoomService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.carteleradaw.springboot.web.app.utils.Utils.isAuth;
import static com.carteleradaw.springboot.web.app.utils.Utils.stringIsEmpty;

/**
 * Controlador de ruta para estrenos.
 */
@RequiredArgsConstructor
@Scope("session")
@Controller
public class PremiereController {

    private final GlobalStateServiceImpl globalStateService;
    private final IRoomService roomService;

    /**
     * Lista los últimnos seis estrenos.
     * @param model Modelo.
     * @return Plantilla index.
     */
    @GetMapping("/")
    public String findAll(Model model) {
        if (isAuth()) globalStateService.updateCitiesNames();

        model.addAttribute("cities", globalStateService.getCitiesNames());
        model.addAttribute("selectedCity", globalStateService.getSelectedCity());
        model.addAttribute("returnUrl", "/");

        List<Room> premieres = roomService.findAllByPremiereDescDistinct(globalStateService.getSelectedCity());

        if (!premieres.isEmpty()) {

            if (premieres.size() > 4) premieres = premieres.subList(0, 4);
            model.addAttribute("premieres", premieres);

        } else model.addAttribute("error", "\uD83E\uDD74 No hay ninguna sala que mostrar");

        return "index";
    }

    /**
     * Actualiza la variable de sesión selectedCity con el valor seleccionado.
     * @param selectedCity ciudad seleccionada.
     * @return Plantilla index.
     */
    @PostMapping("/setCity")
    public String setSelectedCity(@RequestParam("cities") String selectedCity,
                                  @RequestParam(value = "returnUrl", required = false) String returnUrl) {

        globalStateService.setSelectedCity(selectedCity);

        if (!stringIsEmpty(returnUrl)) {
            return "redirect:" + returnUrl;
        } else {
            return "redirect:/";
        }
    }
}
