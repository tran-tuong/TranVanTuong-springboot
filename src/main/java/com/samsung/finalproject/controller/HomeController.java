package com.samsung.finalproject.controller;

import com.samsung.finalproject.models.viewmodels.Users;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    AuthenticationManager authenticationManager;
    public HomeController(AuthenticationManager authenticationManager) {

        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model, @RequestParam(defaultValue = "") String error)
    {
        Users user = new Users();
        model.addAttribute("user", user);
        model.addAttribute("error", error);

        return "Login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model) {

        try {
            // Create authentication token
            Authentication auth = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authenticated = authenticationManager.authenticate(auth);
            SecurityContextHolder.getContext().setAuthentication(authenticated);
            return "redirect:/";

        } catch (Exception e) {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
}
