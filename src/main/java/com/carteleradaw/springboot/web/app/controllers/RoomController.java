package com.carteleradaw.springboot.web.app.controllers;

import com.carteleradaw.springboot.web.app.entities.Room;
import com.carteleradaw.springboot.web.app.services.ICinemaService;
import com.carteleradaw.springboot.web.app.services.IFilmService;
import com.carteleradaw.springboot.web.app.services.IRoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.carteleradaw.springboot.web.app.utils.Utils.invalidPosNumber;

@AllArgsConstructor
@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final IRoomService roomService;
    private final ICinemaService cinemaService;
    private final IFilmService filmService;

    /**
     * Lista todas las salas.
     * @param model Modelo.
     * @return Plantilla rooms-list.
     */
    @GetMapping("")
    public String findAll(Model model) {
        List<Room> rooms = roomService.findAll();
        model.addAttribute("rooms", rooms);
        return "room/room-list";
    }

    /**
     * Muestra una sala específica.
     * @param model Modelo.
     * @param id Identificador.
     * @return Plantilla room-detail.
     */
    @GetMapping("/{id}")
    public String findById(Model model, @PathVariable Long id) {
        if (!invalidPosNumber(id) && roomService.existsById(id))
            model.addAttribute("room", roomService.findById(id).get());
        else model.addAttribute("error", "Sala no encontrada.");
        return "room/room-detail";
    }

    /**
     * Muestra las salas de una película por su ID.
     * @param model Modelo.
     * @param id Identificador.
     * @return Plantilla rooms-list.
     */
    @GetMapping("/film/{id}")
    public String findByFilmId(Model model, @PathVariable Long id) {
        model.addAttribute("rooms", roomService.findAllByFilmId(id));
        return "room/room-list";
    }

    /**
     * Muestra las salas de un cine por su ID.
     * @param model Modelo.
     * @param id Identificador.
     * @return Plantilla rooms-list.
     */
    @GetMapping("/cinema/{id}")
    public String findByCinemaId(Model model, @PathVariable Long id) {
        model.addAttribute("rooms", roomService.findAllByCinemaId(id));
        return "room/room-list";
    }

    /**
     * Crea una nueva sala.
     * @param model modelo.
     * @return Plantilla rooms-form.
     */
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("cinemas", cinemaService.findAll());
        model.addAttribute("films", filmService.findAll());
        return "room/room-form";
    }

    /**
     * Edita una sala existente.
     * @param model Modelo.
     * @param id Identificador.
     * @return Plantilla room-form.
     */
    @GetMapping("/{id}/edit")
    public String editForm(Model model, @PathVariable Long id) {
        if (!invalidPosNumber(id) && roomService.existsById(id)) {
            model.addAttribute("room", roomService.findById(id).get());
            model.addAttribute("cinemas", cinemaService.findAll());
            model.addAttribute("films", filmService.findAll());
        } else model.addAttribute("error", "Sala no encontrada.");
        return "room/room-form";
    }

    /**
     * Guarda la sala obtenida desde el formulario.
     * @param room Dirección.
     * @return Plantilla rooms.
     */
    @PostMapping("")
    public String saveForm(@ModelAttribute Room room) {
        roomService.save(room);
        return "redirect:/rooms";
    }

    /**
     * Borra una sala por su ID.
     * @param id Identificador.
     * @return Plantilla rooms.
     */
    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        if (!invalidPosNumber(id) && roomService.existsById(id)) roomService.deleteById(id);
        return "redirect:/rooms";
    }
}
