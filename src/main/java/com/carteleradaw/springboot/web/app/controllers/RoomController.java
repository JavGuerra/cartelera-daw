package com.carteleradaw.springboot.web.app.controllers;

import com.carteleradaw.springboot.web.app.entities.Room;
import com.carteleradaw.springboot.web.app.services.impl.GlobalStateServiceImpl;
import com.carteleradaw.springboot.web.app.services.ICinemaService;
import com.carteleradaw.springboot.web.app.services.IFilmService;
import com.carteleradaw.springboot.web.app.services.IRoomService;
import com.carteleradaw.springboot.web.app.utils.PageInfo;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

import static com.carteleradaw.springboot.web.app.utils.Utils.*;

/**
 * Controladores de rutas para salas.
 */
@AllArgsConstructor
@Scope("session")
@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final GlobalStateServiceImpl globalStateService;
    private final PageInfo pageInfoComponent;

    private final IRoomService roomService;
    private final ICinemaService cinemaService;
    private final IFilmService filmService;

    private final String startTime = "08:00";
    private final Long interval = 120L;

    /**
     * Lista las salas.
     * @param page Número de página en la paginación.
     * @param size Número de elementos por página.
     * @param model Modelo.
     * @return Plantilla rooms-list.
     */
    @GetMapping("")
    public String findAll(@RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "10") int size,
                          Model model) {

        Pageable paging = PageRequest.of(page, size);
        Page<Room> rooms = roomService.findAllByCity(globalStateService.getSelectedCity(), paging);

        if (!rooms.isEmpty()) {
            PageInfo pageInfo = pageInfoComponent.createFromPage(rooms);

            model.addAttribute("cities", globalStateService.getCitiesNames());
            model.addAttribute("selectedCity", globalStateService.getSelectedCity());
            model.addAttribute("page", pageInfo);
            model.addAttribute("rooms", rooms);
            model.addAttribute("returnUrl", "rooms");

        } else model.addAttribute("error", "\uD83E\uDD74 No hay ninguna sala que mostrar");

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

            model.addAttribute("cities", globalStateService.getCitiesNames());
            model.addAttribute("selectedCity", globalStateService.getSelectedCity());
            model.addAttribute("room", roomService.findById(id).get());
            model.addAttribute("returnUrl", "rooms");

        } else model.addAttribute("error", "\uD83E\uDD74 Sala no encontrada");

        return "room/room-detail";
    }

    /**
     * Muestra las salas de una película por su ID.
     * @param page Número de página en la paginación.
     * @param size Número de elementos por página.
     * @param id Identificador.
     * @param model Modelo.
     * @return Plantilla rooms-list.
     */
    @GetMapping("/film/{id}")
    public String findByFilmId(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               @PathVariable Long id, Model model) {

        if (!invalidPosNumber(id) && filmService.existsById(id)) {

            Pageable paging = PageRequest.of(page, size);
            Page<Room> rooms = roomService.findAllByFilmAndCity(id, globalStateService.getSelectedCity(), paging);

            if (!rooms.isEmpty()) {
                PageInfo pageInfo = pageInfoComponent.createFromPage(rooms);

                model.addAttribute("cities", globalStateService.getCitiesNames());
                model.addAttribute("selectedCity", globalStateService.getSelectedCity());
                model.addAttribute("page", pageInfo);
                model.addAttribute("rooms", rooms);
                model.addAttribute("returnUrl", "rooms");

            } else if (isAuth()) model.addAttribute("error", "\uD83E\uDD74 Película sin salas");
            else model.addAttribute("error", "\uD83E\uDD74 Película no encontrada");
        } else model.addAttribute("error", "\uD83E\uDD74 Película no encontrada");

        return "room/room-list";
    }

    /**
     * Muestra las salas de un cine por su ID.
     * @param page Número de página en la paginación.
     * @param size Número de elementos por página.
     * @param id Identificador.
     * @param model Modelo.
     * @return Plantilla rooms-list.
     */
    @GetMapping("/cinema/{id}")
    public String findByCinemaId(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @PathVariable Long id, Model model) {

        if (!invalidPosNumber(id) && cinemaService.existsById(id)) {

            Pageable paging = PageRequest.of(page, size);
            Page<Room> rooms = roomService.findAllByCinemaId(id, paging);

            if (!rooms.isEmpty()) {
                PageInfo pageInfo = pageInfoComponent.createFromPage(rooms);
                // Al seleccionar las exhibiciones de un cine, se selecciona la ciudad de ese cine.
                globalStateService.setSelectedCity(cinemaService.findById(id).get().getAddress().getCity());

                model.addAttribute("cities", globalStateService.getCitiesNames());
                model.addAttribute("selectedCity", globalStateService.getSelectedCity());
                model.addAttribute("page", pageInfo);
                model.addAttribute("rooms", rooms);
                model.addAttribute("returnUrl", "rooms");

            } else if (isAuth()) model.addAttribute("error", "\uD83E\uDD74 Cine sin salas");
            else model.addAttribute("error", "\uD83E\uDD74 Cine no encontrado");
        } else model.addAttribute("error", "\uD83E\uDD74 Cine no encontrado");

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
        Byte nextRoomNumber = roomService.getNextRoomNumber();

        model.addAttribute("cities", globalStateService.getCitiesNames());
        model.addAttribute("selectedCity", globalStateService.getSelectedCity());
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
            Room room = roomService.findById(id).get();
            Long cinemaId = room.getCinema().getId();

            model.addAttribute("room", room);
            model.addAttribute("cities", globalStateService.getCitiesNames());
            model.addAttribute("selectedCity", globalStateService.getSelectedCity());
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
