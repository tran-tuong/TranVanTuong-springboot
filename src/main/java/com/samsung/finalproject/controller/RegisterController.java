package com.samsung.finalproject.controller;

import com.samsung.finalproject.models.viewmodels.Users;
import com.samsung.finalproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new Users());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute Users user,
                               @RequestParam String confirmPassword,
                               Model model) {
        boolean success = userService.registerUser(user, confirmPassword);

        if (!success) {
            model.addAttribute("error", "Registration failed! Username may already exist or passwords do not match.");
            return "register";
        }

        return "redirect:/login?success"; //
    }
}
