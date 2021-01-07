package com.example.company_lab3.controllers;

import com.example.company_lab3.entity.Worker;
import com.example.company_lab3.repository.WorkerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompanyController {

    @Autowired
    private WorkerRepository workerRepository;

    @GetMapping("/tables")
    public String tableMain(Model model) {
        Iterable<Worker> workers = workerRepository.findAll();
            model.addAttribute("workers", workers);
            return "tables-main";
        }
}
