package com.carteleradaw.springboot.web.app.controllers;

import com.carteleradaw.springboot.web.app.entities.Room;
import com.carteleradaw.springboot.web.app.services.GlobalStateService;
import com.carteleradaw.springboot.web.app.services.IRoomService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static com.carteleradaw.springboot.web.app.utils.Utils.isAuth;
import static com.carteleradaw.springboot.web.app.utils.Utils.stringIsEmpty;

@AllArgsConstructor
@Scope("session")
@Controller
public class PremiereController {

    private final GlobalStateService globalStateService;
    private final IRoomService roomService;

    /**
     * Lista los últimnos seis estrenos.
     * @param model Modelo.
     * @return Plantilla index.
     */
    @GetMapping("/")
    public String findAll(Model model) {
        if (isAuth()) globalStateService.updateCitiesNames();
        Set<String> citiesNames = globalStateService.getCitiesNames();
        String selectedCity = globalStateService.getSelectedCity();
        List<Room> premieres = roomService.findAllByPremiereDescDistinct(selectedCity);
        if (premieres.size() > 6) premieres = premieres.subList(0, 6);
        model.addAttribute("cities", citiesNames);
        model.addAttribute("selectedCity", selectedCity);
        model.addAttribute("premieres", premieres);
        return "index";
    }

    /**
     * Actualiza la variable de sesión selectedCity con el valor seleccionado.
     * @param selectedCity.
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
