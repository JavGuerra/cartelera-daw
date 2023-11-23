package cartelera.controllers;

import cartelera.entities.Room;
import cartelera.services.ICinemaService;
import cartelera.services.IFilmService;
import cartelera.services.IRoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static cartelera.utils.Utils.invalidPosNumber;

@AllArgsConstructor
@Controller
public class RoomController {

    private final IRoomService roomService;
    private final ICinemaService cinemaService;
    private final IFilmService filmService;

    /**
     * Lista todas las salas.
     * @param model Modelo.
     * @return Plantilla rooms-list.
     */
    @GetMapping("/rooms")
    public String findAll(Model model) {
        List<Room> rooms = roomService.findAll();
        model.addAttribute("rooms", rooms);
        return "room/rooms-list";
    }

    /**
     * Muestra una sala específica.
     * @param model Modelo.
     * @param id Identificador.
     * @return Plantilla room-detail.
     */
    @GetMapping("/room/{id}")
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
    @GetMapping("rooms/film/{id}")
    public String findByFilmId(Model model, @PathVariable Long id) {
        model.addAttribute("rooms", roomService.findAllByFilmId(id));
        return "room/rooms-list";
    }

    /**
     * Muestra las salas de un cine por su ID.
     * @param model Modelo.
     * @param id Identificador.
     * @return Plantilla rooms-list.
     */
    @GetMapping("rooms/cinema/{id}")
    public String findByCinemaId(Model model, @PathVariable Long id) {
        model.addAttribute("rooms", roomService.findAllByCinemaId(id));
        return "room/rooms-list";
    }

    /**
     * Crea una nueva sala.
     * @param model modelo.
     * @return Plantilla rooms-form.
     */
    @GetMapping("rooms/create")
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
    @GetMapping("rooms/{id}/edit")
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
    @PostMapping("/rooms")
    public String saveForm(@ModelAttribute Room room) {
        roomService.save(room);
        return "redirect:/rooms";
    }

    /**
     * Borra una sala por su ID.
     * @param id Identificador.
     * @return Plantilla rooms.
     */
    @GetMapping("rooms/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        if (!invalidPosNumber(id) && roomService.existsById(id)) roomService.deleteById(id);
        return "redirect:/rooms";
    }
}
