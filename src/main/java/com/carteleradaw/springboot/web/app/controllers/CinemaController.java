package com.carteleradaw.springboot.web.app.controllers;

import com.carteleradaw.springboot.web.app.entities.Cinema;
import com.carteleradaw.springboot.web.app.entities.Room;
import com.carteleradaw.springboot.web.app.services.GlobalStateService;
import com.carteleradaw.springboot.web.app.services.ICinemaService;
import com.carteleradaw.springboot.web.app.services.IRoomService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static com.carteleradaw.springboot.web.app.utils.Utils.*;

@AllArgsConstructor
@Scope("session")
@Controller
@RequestMapping("/cinemas")
public class CinemaController {

    private final GlobalStateService globalStateService;
    private final ICinemaService cinemaService;
    private final IRoomService roomService;

    /**
     * Lista todos los cines.
     * @param model Modelo.
     * @return Plantilla cinemas-list.
     */
    @GetMapping("")
    public String findAll(Model model) {
        Set<String> citiesNames = globalStateService.getCitiesNames();
        String selectedCity = globalStateService.getSelectedCity();
        List<Cinema> cinemas = cinemaService.findAllByCity(selectedCity);
        model.addAttribute("cities", citiesNames);
        model.addAttribute("selectedCity", selectedCity);
        model.addAttribute("cinemas", cinemas);
        model.addAttribute("returnUrl", "cinemas");
        return "cinema/cinema-list";
    }

    /**
     * Muestra un cine específico.
     * @param model Modelo.
     * @param id Identificador.
     * @return Plantilla cinema-detail.
     */
    @GetMapping("/{id}")
    public String findById(Model model, @PathVariable Long id) {
        if (!invalidPosNumber(id) && cinemaService.existsById(id)) {
            Set<String> citiesNames = globalStateService.getCitiesNames();
            String selectedCity = globalStateService.getSelectedCity();
            Cinema cinema = cinemaService.findById(id).get();
            List<Room> rooms = roomService.findAllByCinemaId(id);
            model.addAttribute("cities", citiesNames);
            model.addAttribute("selectedCity", selectedCity);
            model.addAttribute("cinema", cinema);
            model.addAttribute("rooms", rooms);
            model.addAttribute("returnUrl", "cinemas");
        } else model.addAttribute("error", "Cine no encontrado.");
        return "cinema/cinema-detail";
    }

    /**
     * Crea un nuevo cine.
     * @param model modelo.
     * @return Plantilla cinema-form.
     */
    @GetMapping("/create")
    public String createForm(Model model) {
        Set<String> citiesNames = globalStateService.getCitiesNames();
        String selectedCity = globalStateService.getSelectedCity();
        model.addAttribute("cities", citiesNames);
        model.addAttribute("selectedCity", selectedCity);
        model.addAttribute("cinema", new Cinema());
        model.addAttribute("returnUrl", "cinemas");
        return "cinema/cinema-form";
    }

    /**
     * Edita un cine existente.
     * @param model Modelo.
     * @param id Identificador.
     * @return Plantilla cinema-form.
     */
    @GetMapping("/{id}/edit")
    public String editForm(Model model, @PathVariable Long id) {
        if (!invalidPosNumber(id) && cinemaService.existsById(id)) {
            Set<String> citiesNames = globalStateService.getCitiesNames();
            String selectedCity = globalStateService.getSelectedCity();
            model.addAttribute("cities", citiesNames);
            model.addAttribute("selectedCity", selectedCity);
            model.addAttribute("cinema", cinemaService.findById(id).get());
            model.addAttribute("returnUrl", "cinemas");
        } else model.addAttribute("error", "Cine no encontrado.");
        return "cinema/cinema-form";
    }

    /**
     * Guarda el cine obtenido desde el formulario.
     * @param cinema Cine.
     * @return Plantilla cinemas.
     */
    @PostMapping("")
    public String saveForm(@ModelAttribute Cinema cinema) {
        cinemaService.save(cinema);
        return "redirect:/cinemas";
    }

    /**
     * Borra un cine por su ID.
     * @param id Identificador.
     * @return Plantilla cinemas.
     */
    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        if (!invalidPosNumber(id) && cinemaService.existsById(id))
            cinemaService.deleteById(id);
        return "redirect:/cinemas";
    }
}