package com.carteleradaw.springboot.web.app.controllers;

import com.carteleradaw.springboot.web.app.entities.Room;
import com.carteleradaw.springboot.web.app.services.IAddressService;
import com.carteleradaw.springboot.web.app.services.IRoomService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static com.carteleradaw.springboot.web.app.utils.Utils.isAuth;
import static com.carteleradaw.springboot.web.app.utils.Utils.stringIsEmpty;

/**
 * Controlador de ruta para estrenos.
 */
@RequiredArgsConstructor
@Controller
public class PremiereController {

    private final IAddressService addressService;
    private final IRoomService roomService;

    /**
     * Lista los últimos estrenos.
     * @param session Sesión HTTP.
     * @param model Modelo.
     * @return Plantilla index.
     */
    @GetMapping("/")
    public String findAll(HttpSession session, Model model) {

        if (isAuth()) session.setAttribute("citiesNames", addressService.getCitiesNames());

        model.addAttribute("returnUrl", "/");

        String city = (String) session.getAttribute("selectedCity");
        List<Room> premieres = roomService.findAllByPremiereDescDistinct(city);

        if (!premieres.isEmpty()) {

            if (premieres.size() > 4) premieres = premieres.subList(0, 4);
            model.addAttribute("premieres", premieres);

        } else model.addAttribute("error", "\uD83E\uDD74 No hay ninguna sala que mostrar");

        return "index";
    }

    /**
     * Actualiza la variable de sesión selectedCity con el valor seleccionado.
     * @param session Sesión HTTP.
     * @param selectedCity ciudad seleccionada.
     * @return Plantilla index.
     */
    @PostMapping("/setCity")
    public String setSelectedCity(HttpSession session, @RequestParam("cities") String selectedCity,
                                  @RequestParam(value = "returnUrl", required = false) String returnUrl) {

        if (selectedCity.trim().isEmpty()) session.setAttribute("selectedCity", "");
        else {
            String lowerCaseSelectedCity = selectedCity.toLowerCase();
            Set<String> cities = addressService.getCitiesNames();

            if (cities.stream().anyMatch(city -> city.toLowerCase().equals(lowerCaseSelectedCity)))
                session.setAttribute("selectedCity", selectedCity);
        }

        if (!stringIsEmpty(returnUrl)) {
            return "redirect:" + returnUrl;
        } else {
            return "redirect:/";
        }
    }
}
