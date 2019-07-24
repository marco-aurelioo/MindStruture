package com.tiozao.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }

}
