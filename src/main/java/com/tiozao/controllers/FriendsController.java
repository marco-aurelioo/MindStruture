package com.tiozao.controllers;

import com.tiozao.entities.FriendListEntity;
import com.tiozao.entities.MessageEntity;
import com.tiozao.model.UserModel;
import com.tiozao.services.FriendsService;
import com.tiozao.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.util.UUID;

@Controller
@RequestMapping("/amigos")
public class FriendsController {

    @Autowired
    private UserService userService;

    @Autowired
    private FriendsService friends;

    @GetMapping("/meus-amigos")
    public String meusAmigos(Model model) {
        UserModel sessionUser = userService.getSessionUser();
        model.addAttribute("user", sessionUser);
        model.addAttribute("msgs",friends.findMyUnreadMsgs(sessionUser));
        return "amigos";
    }

    @GetMapping(value = "/meus-amigos/json", produces = "application/json")
    public ResponseEntity<FriendListEntity> meusAmigosJson() {
        UserModel sessionUser = userService.getSessionUser();
        FriendListEntity myFriends = friends.findMyFriends(sessionUser);
        return ResponseEntity.ok(myFriends);
    }

    @PostMapping("solicitacao")
    public ResponseEntity<String> solicitacaoAmizade(@RequestBody String email) {
        UserModel sessionUser = userService.getSessionUser();
        email = email.replace("=", "");
        email = URLDecoder.decode(email);
        friends.solicitacaoAmizade(email, sessionUser);
        return ResponseEntity.ok("sucesso");
    }



    @PostMapping("/confirmacao")
    public ResponseEntity<String> confirmaAmizade(@RequestBody String msgId) {
        UserModel sessionUser = userService.getSessionUser();
        friends.confirmacaoAmizade(UUID.fromString(msgId.replace("=", "")), sessionUser);
        return ResponseEntity.ok("Sucesso");
    }

}
