package com.tiozao.services.security;

import com.tiozao.model.UserModel;
import com.tiozao.services.UserService;
import com.tiozao.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class SimpleConnectionSignUp implements ConnectionSignUp {

    private UserService userService;

    public SimpleConnectionSignUp(UserService userService){
        this.userService = userService;
    }

    public String execute(Connection<?> connection) {
        Connection<Facebook> fbConnection = (Connection<Facebook>) connection;
        UserModel model = new UserModel();
        model.setAvatarUrl(fbConnection.getImageUrl());

        Facebook facebook = fbConnection.getApi();
        String[] fields = { "id", "email", "first_name", "last_name" };
        User me = facebook.fetchObject("me", User.class, fields);

        model.setName(fbConnection.getDisplayName());
        model.setEmail(me.getEmail());

        model.setPassword("facebook_psw");
        model.setConfirmPassword("facebook_psw");
        userService.save(model);

        return fbConnection.getKey().getProviderUserId();
    }
}
