package org.financemanager.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.financemanager.entity.Role;
import org.financemanager.entity.User;
import org.financemanager.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    public static final Logger logger = LogManager.getLogger(UserController.class);
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<User> saveUser(@Valid @ModelAttribute User user, BindingResult bindingResult){
        logger.info("Creating new user");
        user.setRole(Role.USER);
        if(bindingResult.hasErrors()){
            logger.error("Provided user has errors");
            logger.info(bindingResult.getAllErrors());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return new ResponseEntity<>(userRepo.save(user), HttpStatus.CREATED);
    }
}
