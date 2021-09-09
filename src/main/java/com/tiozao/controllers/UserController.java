package com.tiozao.controllers;

import com.tiozao.component.UserValidadorComponent;
import com.tiozao.model.UserModel;
import com.tiozao.services.security.SecurityService;
import com.tiozao.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

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
    public String registration(@ModelAttribute("user") UserModel userForm,Model model) {
        List<String> errors = new ArrayList<>();
        userValidador.validate(userForm,errors);
        if (!errors.isEmpty()) {
            model.addAttribute("errors",errors);
            return "register";
        }
        userService.save(userForm);
        securityService.autoLogin(userForm.getEmail(), userForm.getPassword());
        return "redirect:/usuario";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Eita seu login ou senha deram errado !?");
        if (logout != null)
            model.addAttribute("message", "Volte sempre seu logout foi efetuado com sucesso!!");
        return "login";
    }

    @GetMapping("/usuario")
    public String getMeuCadastro(Model model){
        model.addAttribute("user", userService.getSessionUser());
        return "usuario";
    }

    @PostMapping("/usuario")
    public String postMeuCadastro(@ModelAttribute("user") UserModel userForm, Model model){
        List<String> errors = userService.updateUser(userForm);
        model.addAttribute("user",userForm);
        model.addAttribute("errors",errors);
        return "usuario";
    }
}
