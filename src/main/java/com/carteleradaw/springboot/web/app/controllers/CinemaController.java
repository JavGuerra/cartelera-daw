package com.carteleradaw.springboot.web.app.controllers;

import com.carteleradaw.springboot.web.app.entities.Cinema;
import com.carteleradaw.springboot.web.app.services.IAddressService;
import com.carteleradaw.springboot.web.app.services.ICinemaService;
import com.carteleradaw.springboot.web.app.services.IRoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.carteleradaw.springboot.web.app.utils.Utils.invalidPosNumber;

@AllArgsConstructor
@Controller
@RequestMapping("/cinemas")
public class CinemaController {

    private final ICinemaService cinemaService;
    private final IRoomService roomService;
    private final IAddressService addressService;

    /**
     * Lista todos los cines.
     * @param model Modelo.
     * @return Plantilla cinemas-list.
     */
    @GetMapping("")
    public String findAll(Model model) {
        List<Cinema> cinemas = cinemaService.findAll();
        model.addAttribute("cinemas", cinemas);
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
            model.addAttribute("cinema", cinemaService.findById(id).get());
            model.addAttribute("rooms", roomService.findAllByCinemaId(id));
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
        model.addAttribute("cinema", new Cinema());
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
        if (!invalidPosNumber(id) && cinemaService.existsById(id))
            model.addAttribute("cinema", cinemaService.findById(id).get());
        else model.addAttribute("error", "Cine no encontrado.");
        return "cinema/cinema-form";
    }

    /**
     * Guarda el cine obtenido desde el formulario.
     * @param cinema Cine.
     * @return Plantilla cinemas.
     */
    @PostMapping("")
    public String saveForm(@ModelAttribute Cinema cinema) {
        addressService.save(cinema.getAddress());
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
        if (!invalidPosNumber(id) && cinemaService.existsById(id)) cinemaService.deleteById(id);
        return "redirect:/cinemas";
    }
}