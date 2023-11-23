package cartelera.controllers;

import cartelera.entities.User;
import cartelera.services.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static cartelera.utils.Utils.*;

@AllArgsConstructor
@Controller
public class UserController {

    private final IUserService userService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Lista todos los usuarios.
     * @param model Modelo.
     * @return Plantilla users-list.
     */
    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/users-list";
    }

    /**
     * Muestra un usuario específico.
     * @param model Modelo.
     * @param id Identificador.
     * @return Plantilla user-detail.
     */
    @GetMapping("/user/{id}")
    public String findById(Model model, @PathVariable Long id) {
        if (!invalidPosNumber(id) && userService.existsById(id))
            model.addAttribute("user", userService.findById(id).get());
        else model.addAttribute("error", "Usuario no encontrado.");
        return "user/user-detail";
    }

    /**
     * Crea un nuevo usuario.
     * @param model modelo.
     * @return Plantilla user-form.
     */
    @GetMapping("users/create")
    public String createForm(Model model) {
        model.addAttribute("user", new User());
        return "user/user-form";
    }

    /**
     * Edita un usuario existente.
     * @param model Modelo.
     * @param id Identificador.
     * @return Plantilla user-form.
     */
    @GetMapping("users/{id}/edit")
    public String editForm(Model model, @PathVariable Long id) {
        if (!invalidPosNumber(id) && userService.existsById(id))
            model.addAttribute("user", userService.findById(id).get());
        else model.addAttribute("error", "Usuario no encontrado.");
        return "user/user-form";
    }

    /**
     * Guarda el usuario obtenido desde el formulario.
     * @param user Dirección.
     * @return Plantilla users.
     */
    @PostMapping("users")
    public String save(Model model, @ModelAttribute User user) {

        Long id = user.getId();
        String username = user.getUsername();
        String email = user.getEmail();

        boolean userExist = userService.existsById(id);
        boolean existsByUsername = userService.existsByUsername(username);
        boolean existsByEmail = userService.existsByEmail(email);

        // Si el usuario es nuevo, pero ya existe el nombre de usuario o el correo...
        if (!userExist && (existsByUsername || existsByEmail)) {
            model.addAttribute("error", "El usuario ya existe.");
            return "user/user-form";
        }

        Long idByUsername = (existsByUsername) ? userService.findByUsername(username).get().getId() : id;
        Long idByEmail = (existsByEmail) ? userService.findByEmail(email).get().getId() : id;

        // Si el usuario ya existe, pero se cambió su nombre de usuario o su correo a otra que ya existía...
        if (userExist && ( (existsByUsername && (idByUsername != id)) || (existsByEmail && (idByEmail != id)) )) {
            model.addAttribute("error", "El nombre de usuario o el correo ya está en uso.");
            return "user/user-form";
        }

        String oldPasswd = (userExist) ? userService.findById(id).get().getPassword() : null;
        String newPasswd = user.getPassword();

        // Permite cambiar contraseña
        if (!stringIsEmpty(newPasswd)) user.setPassword(passwordEncoder.encode(newPasswd)); // Cambia contraseña
        else if (!stringIsEmpty(oldPasswd)) user.setPassword(oldPasswd); // Mantiene contraseña actual
        else user.setPassword("$2a$10$dsQX4tLUoI9qFpRXhdRYcOpM1ORFAU60Jtr/WSn.g0mY6ADvZsa5q"); // por defecto

        userService.save(user);

        return "redirect:/users";
    }

    /**
     * Borra un usuario por su ID.
     * @param id Identificador.
     * @return Plantilla users.
     */
    @GetMapping("users/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        if (!invalidPosNumber(id) && userService.existsById(id)) {
            Long loginId = null;

            // Comprueba si hay un usuario logueado y, si es así, obtiene su id.
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                User admin = (User) auth.getPrincipal();
                loginId = admin.getId();
            }

            // Si el usuario está logueado y el usuario que queremos borrar NO es el mismo, borrarlo.
            if (!id.equals(loginId)) userService.deleteById(id);
        }
        return "redirect:/users";
    }
}
