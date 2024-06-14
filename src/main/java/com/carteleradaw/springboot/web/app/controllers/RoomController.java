package com.carteleradaw.springboot.web.app.controllers;

import com.carteleradaw.springboot.web.app.entities.Room;
import com.carteleradaw.springboot.web.app.services.GlobalStateService;
import com.carteleradaw.springboot.web.app.services.ICinemaService;
import com.carteleradaw.springboot.web.app.services.IFilmService;
import com.carteleradaw.springboot.web.app.services.IRoomService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import static com.carteleradaw.springboot.web.app.utils.Utils.*;

@AllArgsConstructor
@Scope("session")
@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final GlobalStateService globalStateService;
    private final IRoomService roomService;
    private final ICinemaService cinemaService;
    private final IFilmService filmService;

    private final String startTime = "08:00";
    private final Long interval = 120L;

    /**
     * Lista todas las salas.
     * @param model Modelo.
     * @return Plantilla rooms-list.
     */
    @GetMapping("")
    public String findAll(Model model) {
        String selectedCity = globalStateService.getSelectedCity();
        List<Room> rooms = roomService.findAllByCity(selectedCity);
        model.addAttribute("cities", globalStateService.getCitiesNames());
        model.addAttribute("selectedCity", selectedCity);
        model.addAttribute("rooms", rooms);
        model.addAttribute("returnUrl", "rooms");
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
        if (!invalidPosNumber(id) && roomService.existsById(id) && roomService.isVisible(id)) {
            Set<String> citiesNames = globalStateService.getCitiesNames();
            String selectedCity = globalStateService.getSelectedCity();
            model.addAttribute("cities", citiesNames);
            model.addAttribute("selectedCity", selectedCity);
            model.addAttribute("room", roomService.findById(id).get());
            model.addAttribute("returnUrl", "rooms");
        } else model.addAttribute("error", "\uD83E\uDD74 Sala no encontrada");
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
        Set<String> citiesNames = globalStateService.getCitiesNames();
        String selectedCity = globalStateService.getSelectedCity();
        List<Room> rooms = roomService.findAllByFilmAndCity(id, selectedCity);
        model.addAttribute("cities", citiesNames);
        model.addAttribute("selectedCity", selectedCity);
        model.addAttribute("rooms", rooms);
        model.addAttribute("returnUrl", "rooms");
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
        Set<String> citiesNames = globalStateService.getCitiesNames();
        // Al seleccionar las exhibiciones de un cine, se selecciona la ciudad de ese cine.
        globalStateService.setSelectedCity(cinemaService.findById(id).get().getAddress().getCity());
        String selectedCity = globalStateService.getSelectedCity();
        List<Room> rooms = roomService.findAllByCinemaId(id);
        model.addAttribute("cities", citiesNames);
        model.addAttribute("selectedCity", selectedCity);
        model.addAttribute("rooms", rooms);
        model.addAttribute("returnUrl", "rooms");
        return "room/room-list";
    }

    /**
     * Crea una nueva sala.
     * @param model modelo.
     * @return Plantilla rooms-form.
     */
    @GetMapping("/create")
    public String createForm(Model model) {
        List<LocalTime> schedulesList = roomService.generateSchedulesList(startTime, interval);
        Set<String> citiesNames = globalStateService.getCitiesNames();
        String selectedCity = globalStateService.getSelectedCity();
        Byte nextRoomNumber = roomService.getNextRoomNumber();
        model.addAttribute("cities", citiesNames);
        model.addAttribute("selectedCity", selectedCity);
        model.addAttribute("room", new Room());
        model.addAttribute("nextRoomNumber", String.valueOf(nextRoomNumber));
        model.addAttribute("cinemas", cinemaService.findAll());
        model.addAttribute("films", filmService.findAllActive());
        model.addAttribute("schedulesList", schedulesList);
        model.addAttribute("returnUrl", "rooms");
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
            List<LocalTime> schedulesList = roomService.generateSchedulesList(startTime, interval);
            Set<String> citiesNames = globalStateService.getCitiesNames();
            String selectedCity = globalStateService.getSelectedCity();
            Room room = roomService.findById(id).get();
            Long cinemaId = room.getCinema().getId();
            model.addAttribute("room", room);
            model.addAttribute("cities", citiesNames);
            model.addAttribute("selectedCity", selectedCity);
            model.addAttribute("cinemas", cinemaService.findById(cinemaId).get());
            model.addAttribute("films", filmService.findAllActive());
            model.addAttribute("schedulesList", schedulesList);
            model.addAttribute("returnUrl", "rooms");
        } else model.addAttribute("error", "\uD83E\uDD74 Sala no encontrada");
        return "room/room-form";
    }

    /**
     * Guarda la sala obtenida desde el formulario.
     * @param room Dirección.
     * @param result estado de la validación.
     * @return Plantilla rooms.
     */
    @PostMapping("")
    public String saveForm(@Valid @ModelAttribute Room room, BindingResult result) {
        if (result.hasErrors()) {
            return "room/room-form";
        } else {
            roomService.save(room);
            return "redirect:/rooms";
        }
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
