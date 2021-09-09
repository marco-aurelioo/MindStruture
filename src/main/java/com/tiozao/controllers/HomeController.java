package com.tiozao.controllers;

import com.tiozao.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("user", userService.getSessionUser());
        return "home";
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", userService.getSessionUser());
        return "home";
    }

}
