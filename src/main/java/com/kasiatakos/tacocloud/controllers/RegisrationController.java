package com.kasiatakos.tacocloud.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kasiatakos.tacocloud.commands.RegistrationCommand;
import com.kasiatakos.tacocloud.repositories.jpa.UserRepository;

@Controller
@RequestMapping("/register")
public class RegisrationController {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public RegisrationController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationCommand command) {
        userRepo.save(command.toUser(passwordEncoder));
        return "redirect:/login";
    }
}
