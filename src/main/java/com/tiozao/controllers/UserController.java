package com.tiozao.controllers;

import com.tiozao.component.UserValidadorComponent;
import com.tiozao.model.UserModel;
import com.tiozao.services.SecurityService;
import com.tiozao.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserValidadorComponent userValidador;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/cadastro")
    public String registration(Model model) {
        model.addAttribute("user", new UserModel());
        return "register";
    }

    @PostMapping("/cadastro")
    public String registration(@ModelAttribute("user") UserModel userForm, BindingResult bindingResult) {
        userValidador.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getEmail(), userForm.getPassword());

        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        model.addAttribute("user", new UserModel());
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute("user") UserModel userForm, BindingResult bindingResult) {

        securityService.autoLogin(userForm.getEmail(), userForm.getPassword());

        return "home";

    }
}
