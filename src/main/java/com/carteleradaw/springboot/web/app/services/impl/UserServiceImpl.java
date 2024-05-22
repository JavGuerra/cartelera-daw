package com.carteleradaw.springboot.web.app.services.impl;

import com.carteleradaw.springboot.web.app.entities.User;
import com.carteleradaw.springboot.web.app.repositories.UserRepository;
import com.carteleradaw.springboot.web.app.services.IUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.carteleradaw.springboot.web.app.utils.Utils.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepo;

    @Override
    public List<User> findAll() {
        log.info("findAll");
        return userRepo.findAll();
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

    public User save(User user) {
        log.info("save {}", user);
        return userRepo.save(user);
    }

    public void deleteById(Long id) {
        log.info("deleteById {}", id);
        if (!invalidPosNumber(id) && userRepo.existsById(id)) userRepo.deleteById(id);
    }
}
