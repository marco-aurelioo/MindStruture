package com.tiozao.controllers;

import com.tiozao.model.UserModel;
import com.tiozao.repositories.UserRepository;
import com.tiozao.services.FriendsService;
import com.tiozao.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/amigos")
public class FriendsController {

    @Autowired
    private UserService userService;

    @Autowired
    private FriendsService friends;

    @GetMapping("/meus-amigos")
    public String meusAmigos(Model model){
        UserModel sessionUser = userService.getSessionUser();
        model.addAttribute("user", userService.getSessionUser());
        friends.findMyFriends(sessionUser,0,10);
        return "amigos";
    }


}
