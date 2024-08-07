package com.carteleradaw.springboot.web.app.controllers;

import com.carteleradaw.springboot.web.app.entities.Cinema;
import com.carteleradaw.springboot.web.app.entities.Room;
import com.carteleradaw.springboot.web.app.services.ICinemaService;
import com.carteleradaw.springboot.web.app.services.IRoomService;
import com.carteleradaw.springboot.web.app.utils.PageInfo;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.carteleradaw.springboot.web.app.utils.Utils.*;

/**
 * Controladores de rutas para cines.
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/cinemas")
public class CinemaController {

    private final PageInfo pageInfoComponent;

    private final ICinemaService cinemaService;
    private final IRoomService roomService;

    /**
     * Lista todos los cines.
     * @param page Número de página para paginación.
     * @param size Tamaño de página para paginación.
     * @param session Sesión HTTP.
     * @param model Modelo.
     * @return Plantilla cinemas-list.
     */
    @GetMapping("")
    public String findAll(@RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "10") int size,
                          HttpSession session, Model model) {

        model.addAttribute("returnUrl", "cinemas");

        String city = (String) session.getAttribute("selectedCity");
        Pageable paging = PageRequest.of(page, size);
        Page<Cinema> cinemas = cinemaService.findAllByCity(city, paging);

        if (!cinemas.isEmpty()) {
            PageInfo pageInfo = pageInfoComponent.createFromPage(cinemas);

            model.addAttribute("page", pageInfo);
            model.addAttribute("cinemas", cinemas);
            model.addAttribute("entity", "cines");

        } else model.addAttribute("error", "\uD83E\uDD74 No hay ningún cine que mostrar");

        return "cinema/cinema-list";
    }

    /**
     * Muestra un cine específico.
     * @param session Sesión HTTP.
     * @param model Modelo.
     * @param id Identificador.
     * @return Plantilla cinema-detail.
     */
    @GetMapping("/{id}")
    public String findById(HttpSession session, Model model, @PathVariable Long id) {

        model.addAttribute("returnUrl", "cinemas");

        if (!invalidPosNumber(id) && cinemaService.existsById(id) && cinemaService.isVisible(id)) {

            Cinema cinema = cinemaService.findById(id).get();
            List<Room> rooms = roomService.findAllByCinemaId(id, Pageable.unpaged()).getContent();
            //Set<Room> rooms = cinema.getRooms();

            // Al mostrar el cine, se selecciona la ciudad.
            session.setAttribute("selectedCity", cinema.getCity());

            model.addAttribute("cinema", cinema);
            model.addAttribute("rooms", rooms);

        } else model.addAttribute("error", "\uD83E\uDD74 Cine no encontrado");

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

        model.addAttribute("returnUrl", "cinemas");

        if (!invalidPosNumber(id) && cinemaService.existsById(id)) {
            model.addAttribute("cinema", cinemaService.findById(id).get());
        } else model.addAttribute("error", "\uD83E\uDD74 Cine no encontrado");

        return "cinema/cinema-form";
    }

    /**
     * Guarda el cine obtenido desde el formulario.
     * @param cinema Cine.
     * @param result Estado de la validación.
     * @param model Modelo.
     * @return Plantilla cinemas.
     */
    @PostMapping("")
    public String saveForm(@Valid @ModelAttribute Cinema cinema, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("cinema", cinema);
            model.addAttribute("returnUrl", "cinemas");
            return "cinema/cinema-form";
//        } else if (!result.getFieldErrors("address").isEmpty()) { // Ejemplo de validación de un campo
//            return "cinema/cinema-form";
        } else {
            if (cinemaService.existsByCif(cinema.getCif())) {
                Long existingId = cinemaService.findByCif(cinema.getCif()).get().getId();
                if (!Objects.equals(existingId, cinema.getId())) {
                    result.rejectValue("cif", "error.cif", "El CIF indicado ya existe");
                    return "cinema/cinema-form";
                }
            }

            cinemaService.save(cinema);
            if (!cinema.getActive()) roomService.deactivateRoomsByCinemaId(cinema.getId());

            return "redirect:/cinemas/" + cinema.getId();
        }
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