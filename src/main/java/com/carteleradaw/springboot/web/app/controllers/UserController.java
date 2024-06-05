package com.carteleradaw.springboot.web.app.controllers;

import com.carteleradaw.springboot.web.app.entities.User;
import com.carteleradaw.springboot.web.app.services.GlobalStateService;
import com.carteleradaw.springboot.web.app.services.IUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.carteleradaw.springboot.web.app.utils.Utils.*;

@AllArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {

    private final GlobalStateService globalStateService;
    private final IUserService userService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Lista todos los usuarios.
     * @param model Modelo.
     * @return Plantilla users-list.
     */
    @GetMapping("")
    public String findAll(Model model) {
        Set<String> citiesNames = globalStateService.getCitiesNames();
        String selectedCity = globalStateService.getSelectedCity();
        List<User> users = userService.findAll();
        model.addAttribute("cities", globalStateService.getCitiesNames());
        model.addAttribute("selectedCity", selectedCity);
        model.addAttribute("users", users);
        model.addAttribute("returnUrl", "users");
        return "user/user-list";
    }

    /**
     * Muestra un usuario específico.
     * @param model Modelo.
     * @param id Identificador.
     * @return Plantilla user-detail.
     */
    @GetMapping("/{id}")
    public String findById(Model model, @PathVariable Long id) {
        if (!invalidPosNumber(id) && userService.existsById(id)) {
            Set<String> citiesNames = globalStateService.getCitiesNames();
            String selectedCity = globalStateService.getSelectedCity();
            model.addAttribute("cities", citiesNames);
            model.addAttribute("selectedCity", selectedCity);
            model.addAttribute("user", userService.findById(id).get());
            model.addAttribute("returnUrl", "users");
        } else model.addAttribute("error", "Usuario no encontrado.");
        return "user/user-detail";
    }

    /**
     * Crea un nuevo usuario.
     * @param model modelo.
     * @return Plantilla user-form.
     */
    @GetMapping("/create")
    public String createForm(Model model) {
        Set<String> citiesNames = globalStateService.getCitiesNames();
        String selectedCity = globalStateService.getSelectedCity();
        model.addAttribute("cities", citiesNames);
        model.addAttribute("selectedCity", selectedCity);
        model.addAttribute("user", new User());
        model.addAttribute("returnUrl", "users");
        return "user/user-form";
    }

    /**
     * Edita un usuario existente.
     * @param model Modelo.
     * @param id Identificador.
     * @return Plantilla user-form.
     */
    @GetMapping("/{id}/edit")
    public String editForm(Model model, @PathVariable Long id) {
        if (!invalidPosNumber(id) && userService.existsById(id)) {
            Set<String> citiesNames = globalStateService.getCitiesNames();
            String selectedCity = globalStateService.getSelectedCity();
            model.addAttribute("cities", citiesNames);
            model.addAttribute("selectedCity", selectedCity);
            model.addAttribute("user", userService.findById(id).get());
            model.addAttribute("returnUrl", "users");
        } else model.addAttribute("error", "Usuario no encontrado.");
        return "user/user-form";
    }

    /**
     * Guarda el usuario obtenido desde el formulario.
     * @param user Dirección.
     * @param result estado de la validación.
     * @return Plantilla users.
     */
    
    @PostMapping("")
    public String save(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/user-form";
        } else {
            Long id = user.getId();
            String username = user.getUsername();
            String email = user.getEmail();

            boolean userExist = userService.existsById(id);
            boolean existsByUsername = userService.existsByUsername(username);
            boolean existsByEmail = userService.existsByEmail(email);

            // Si el usuario es nuevo, pero ya existe el nombre de usuario o el correo...
            if (!userExist && (existsByUsername || existsByEmail)) {
                model.addAttribute("error", "Ya está en uso.");
                if (existsByUsername)
                    result.rejectValue("username", "error.username", "El usuario ya existe.");
                if (existsByEmail)
                    result.rejectValue("email", "error.email", "El correo ya existe.");
                return "user/user-form";
            }

            Long idByUsername = (existsByUsername) ? userService.findByUsername(username).get().getId() : id;
            Long idByEmail = (existsByEmail) ? userService.findByEmail(email).get().getId() : id;

            // Si el usuario ya existe, pero se cambió su nombre de usuario o su correo a otra que ya existía...
            if (userExist && ((existsByUsername && (!Objects.equals(idByUsername, id))) ||
                    (existsByEmail && (!Objects.equals(idByEmail, id))))) {
                model.addAttribute("error", "Ya está en uso.");
                if (existsByUsername && !Objects.equals(idByUsername, id))
                    result.rejectValue("username", "error.username", "El usuario ya existe.");
                if (existsByEmail && !Objects.equals(idByEmail, id))
                    result.rejectValue("email", "error.email", "El correo ya existe.");
                return "user/user-form";
            }

            String oldPasswd = (userExist) ? userService.findById(id).get().getPassword() : null;
            String newPasswd = user.getPassword();

            // Permite cambiar contraseña
            if (!stringIsEmpty(newPasswd)) user.setPassword(passwordEncoder.encode(newPasswd)); // Cambia contraseña
            else if (!stringIsEmpty(oldPasswd)) user.setPassword(oldPasswd); // Mantiene contraseña actual
            else user.setPassword("$2a$10$uuAkGrH14kAnfPe2d7ApcOrDM2tTjz7JhPxa5yGeuE34j65cT6xFq"); // por defecto

            userService.save(user);

            return "redirect:/users";
        }
    }

    /**
     * Borra un usuario por su ID.
     * @param id Identificador.
     * @return Plantilla users.
     */
    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        if (!invalidPosNumber(id) && userService.existsById(id)) {
            Long loginId = null;
            // Comprueba si hay un usuario logueado y, si es así, obtiene su id.
            if (isAuth()) loginId = userAuth().get().getId();

            // Si el usuario está logueado y el usuario que queremos borrar NO es el mismo, borrarlo.
            if (!id.equals(loginId)) userService.deleteById(id);
        }
        return "redirect:/users";
    }
}
