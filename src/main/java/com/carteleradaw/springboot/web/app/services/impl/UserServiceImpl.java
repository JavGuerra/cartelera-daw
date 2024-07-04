package com.carteleradaw.springboot.web.app.services.impl;

import com.carteleradaw.springboot.web.app.entities.User;
import com.carteleradaw.springboot.web.app.repositories.UserRepository;
import com.carteleradaw.springboot.web.app.services.IUserService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.carteleradaw.springboot.web.app.utils.Utils.*;

import java.util.Optional;

/**
 * Implementación de servicios de usuarios.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

    private final HttpSession session;
    private final UserRepository userRepo;

    @Override
    public Page<User> findAll(Pageable paging) {
        log.info("findAll");
        return userRepo.findAll(paging);
    }

    @Override
    public boolean existsById(Long id) {
        log.info("existsById {}", id);
        if (invalidPosNumber(id)) return false;
        return userRepo.existsById(id);
    }

    @Override
    public Optional<User> findById(Long id) {
        log.info("findById {}", id);
        if (invalidPosNumber(id)) return Optional.empty();
        return userRepo.findById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        log.info("existsByUsername {}", username);
        if (stringIsEmpty(username)) return false;
        return userRepo.existsByUsername(username);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        log.info("findByUsername {}", username);
        if (stringIsEmpty(username)) return Optional.empty();
        return userRepo.findByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        log.info("existsByEmail {}", email);
        if (stringIsEmpty(email)) return false;
        return userRepo.existsByEmail(email);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        log.info("findByEmail {}", email);
        if (stringIsEmpty(email)) return Optional.empty();
        return userRepo.findByEmail(email);
    }

    @Override
    @Transactional
    public User save(User user) {
        log.info("save {}", user);

        try {
            User newUser = userRepo.save(user);

            session.setAttribute("message", "Usuario " + user + " guardado.");
            session.setAttribute("messageType", "info");

            return newUser;

        } catch (DataIntegrityViolationException e) {
            log.error("Error al guardar el usuario: ", e);

            session.setAttribute("message", "El usuario no ha podido guardarse.");
            session.setAttribute("messageType", "danger");

            return null;
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("deleteById {}", id);

        if (invalidPosNumber(id) || !userRepo.existsById(id)) {
            session.setAttribute("message", "Usuario no encontrado.");
            session.setAttribute("messageType", "danger");
            return;
        }

        try {
            User user = findById(id).get();

            userRepo.deleteById(id);

            session.setAttribute("message", "Usuario " + user + " borrado.");
            session.setAttribute("messageType", "info");

        } catch (DataIntegrityViolationException e) {
            log.error("Error al borrar el usuario: ", e);

            session.setAttribute("message", "El usuario no ha podido borrarse.");
            session.setAttribute("messageType", "danger");
        }
    }
}
