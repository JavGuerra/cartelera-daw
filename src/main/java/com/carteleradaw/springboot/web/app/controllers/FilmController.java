package com.carteleradaw.springboot.web.app.controllers;

import com.carteleradaw.springboot.web.app.entities.Film;
import com.carteleradaw.springboot.web.app.services.GlobalStateService;
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

import static com.carteleradaw.springboot.web.app.utils.Utils.*;

@AllArgsConstructor
@Scope("session")
@Controller
@RequestMapping("/films")
public class FilmController {

    private final GlobalStateService globalStateService;
    private final IFilmService filmService;
    private final IRoomService roomService;

    /**
     * Lista todas las películas.
     * @param model Modelo.
     * @return Plantilla film-list.
     */
    @GetMapping("")
    public String findAll(@RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "10") int size,
                          Model model) {

        Pageable paging = PageRequest.of(page, size);
        Page<Film> films = filmService.findAllByCity(globalStateService.getSelectedCity(), paging);
        PageInfo pageInfo = PageInfo.createFromPage(films);

        model.addAttribute("cities", globalStateService.getCitiesNames());
        model.addAttribute("selectedCity", globalStateService.getSelectedCity());
        model.addAttribute("page", pageInfo);
        model.addAttribute("films", films);
        model.addAttribute("returnUrl", "films");

        return "film/film-list";
    }

    /**
     * Muestra una película específica.
     * @param model Modelo.
     * @param id Identificador.
     * @return Plantilla film-detail.
     */
    @GetMapping("/{id}")
    public String findById(Model model, @PathVariable Long id) {
        // List<Film> filmOpt = filmService.findByIdWithGenre(id);
        if (!invalidPosNumber(id) && filmService.existsById(id) && filmService.isVisible(id)) {

            model.addAttribute("cities", globalStateService.getCitiesNames());
            model.addAttribute("selectedCity", globalStateService.getSelectedCity());
            model.addAttribute("film", filmService.findById(id).get());
            model.addAttribute("rooms", roomService.findAllByFilmId(id));
            model.addAttribute("returnUrl", "films");

        } else model.addAttribute("error", "\uD83E\uDD74 Película no encontrada");

        return "film/film-detail";
    }

    /**
     * Crea una nueva película.
     * @param model modelo.
     * @return Plantilla film-form.
     */
    @GetMapping("/create")
    public String createForm(Model model) {

        model.addAttribute("cities", globalStateService.getCitiesNames());
        model.addAttribute("selectedCity", globalStateService.getSelectedCity());
        model.addAttribute("film", new Film());
        model.addAttribute("returnUrl", "films");

        return "film/film-form";
    }

    /**
     * Edita una película existente.
     * @param model Modelo.
     * @param id Identificador.
     * @return Plantilla film-form.
     */
    @GetMapping("/{id}/edit")
    public String editForm(Model model, @PathVariable Long id) {
        if (!invalidPosNumber(id) && filmService.existsById(id)){

            model.addAttribute("cities", globalStateService.getCitiesNames());
            model.addAttribute("selectedCity", globalStateService.getSelectedCity());
            model.addAttribute("film", filmService.findById(id).get());
            model.addAttribute("returnUrl", "films");

        } else model.addAttribute("error", "\uD83E\uDD74 Película no encontrada");

        return "film/film-form";
    }

    /**
     * Guarda la película obtenida desde el formulario.
     * @param film Película.
     * @param result estado de la validación.
     * @return Plantilla films.
     */
    @PostMapping("")
    public String saveForm(@Valid @ModelAttribute Film film, BindingResult result) {
        if (result.hasErrors()) {
            return "film/film-form";
        } else {
            if (!film.getActive()) filmService.deactiveById(film.getId());
            filmService.save(film);
            return "redirect:/films";
        }
    }

    /**
     * Borra una película por su ID.
     * @param id Identificador.
     * @return Plantilla films.
     */
    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        if (!invalidPosNumber(id) && filmService.existsById(id)) filmService.deleteById(id);
        return "redirect:/films";
    }
}
