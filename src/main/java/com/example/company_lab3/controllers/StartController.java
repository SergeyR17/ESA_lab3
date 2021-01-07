package com.example.company_lab3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница сервиса");
        return "home";
    }
}
