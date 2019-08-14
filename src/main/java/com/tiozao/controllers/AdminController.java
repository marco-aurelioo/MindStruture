package com.tiozao.controllers;

import com.tiozao.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String getAdmimPage(Model model){
        model.addAttribute("user", userService.getSessionUser());
        return "admin";
    }



}
