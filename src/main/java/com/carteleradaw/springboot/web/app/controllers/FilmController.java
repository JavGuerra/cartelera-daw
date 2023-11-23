package com.carteleradaw.springboot.web.app.controllers;

import com.carteleradaw.springboot.web.app.entities.Film;
import com.carteleradaw.springboot.web.app.services.IFilmService;
import com.carteleradaw.springboot.web.app.services.IRoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import static com.carteleradaw.springboot.web.app.utils.Utils.*;

import java.util.List;

@AllArgsConstructor
@Controller
public class FilmController {

    private final IFilmService filmService;
    private final IRoomService roomService;

    /**
     * Lista todas las películas.
     * @param model Modelo.
     * @return Plantilla film-list.
     */
    @GetMapping("/films")
    public String findAll(Model model) {
        List<Film> films = filmService.findAll();
        model.addAttribute("films", films);
        // model.addAttribute("cities", addressService.citiesNames());
        return "film/film-list";
    }

    /**
     * Muestra una película específica.
     * @param model Modelo.
     * @param id Identificador.
     * @return Plantilla film-detail.
     */
    @GetMapping("film/{id}")
    public String findById(Model model, @PathVariable Long id) {
        // List<Film> filmOpt = filmService.findByIdWithGender(id);
        if (!invalidPosNumber(id) && filmService.existsById(id)) {
            model.addAttribute("film", filmService.findById(id).get());
            model.addAttribute("rooms", roomService.findAllByFilmId(id));
        } else model.addAttribute("error", "Película no encontrada.");
        return "film/film-detail";
    }

//    @GetMapping("films/genders/{gender}")
//    public String findByGender(Model model, @PathVariable String gender) {
//        model.addAttribute("films", filmService.findAllByGenders(gender));
//        return "film/film-list";
//    }

//    @GetMapping("/films/{city}")
//    public String findAllByRoomsCinemaAddressCity(Model model, @PathVariable String city) {
//        if (!stringIsEmpty(city) && addressService.existsCity(city.trim())) {
//            city = firstCharUpercase(city.trim());
//            List<Film> films = filmService.findAllByRoomsCinemaAddressCityIgnoreCase(city);
//            model.addAttribute("city", city);
//            if (!films.isEmpty()) model.addAttribute("films", films);
//            else model.addAttribute("warning", "No hay películas en «" + city + "».");
//        } else model.addAttribute("error", "Ciudad «" + city + "» no encontrada.");
//        return "film/films-city";
//    }

    /**
     * Crea una nueva película.
     * @param model modelo.
     * @return Plantilla film-form.
     */
    @GetMapping("films/create")
    public String createForm(Model model) {
        model.addAttribute("film",new Film());
        return "film/film-form";
    }

    /**
     * Edita una película existente.
     * @param model Modelo.
     * @param id Identificador.
     * @return Plantilla film-form.
     */
    @GetMapping("films/{id}/edit")
    public String editForm(Model model, @PathVariable Long id) {
        if (!invalidPosNumber(id) && filmService.existsById(id))
            model.addAttribute("film", filmService.findById(id).get());
        else model.addAttribute("error", "Película no encontrada.");
        return "film/film-form";
    }

    /**
     * Guarda la película obtenida desde el formulario.
     * @param film Película.
     * @return Plantilla films.
     */
    @PostMapping("films")
    public String saveForm(@ModelAttribute Film film) {
        filmService.save(film);
        return "redirect:/films";
    }

    /**
     * Borra una película por su ID.
     * @param id Identificador.
     * @return Plantilla films.
     */
    @GetMapping("films/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        if (!invalidPosNumber(id) && filmService.existsById(id)) filmService.deleteById(id);
        return "redirect:/films";
    }
}
