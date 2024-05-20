package com.carteleradaw.springboot.web.app.controllers;

import com.carteleradaw.springboot.web.app.entities.Room;
import com.carteleradaw.springboot.web.app.services.IRoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.carteleradaw.springboot.web.app.utils.Utils.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Controller
public class PremiereController {

    private final IRoomService roomService;

    /**
     * Lista los últimnos seis estrenos.
     * @param model Modelo.
     * @return Plantilla index.
     */
    @GetMapping("/")
    public String findAll(Model model) {
        List<Room> premieres = roomService.findAllByPremiereDescDistinct();
        if (premieres.size() > 6) premieres.subList(0, 6);
        model.addAttribute("premieres", premieres);
        // model.addAttribute("cities", addressService.citiesNames());
        return "index";
    }
}
