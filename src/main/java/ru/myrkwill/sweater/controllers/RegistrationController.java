package ru.myrkwill.sweater.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.myrkwill.sweater.entities.Message;
import ru.myrkwill.sweater.entities.Role;
import ru.myrkwill.sweater.entities.User;
import ru.myrkwill.sweater.repositories.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author Mark Nagibin
 */

@Controller
public class RegistrationController {

    private UserRepository userRepository;

    @Autowired
    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(@ModelAttribute("user") User user, Model model) {

        User existUser = userRepository.findByUsername(user.getUsername());

        if (existUser != null) {
            model.addAttribute("message", "User exist!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return "redirect:/login";
    }

}
