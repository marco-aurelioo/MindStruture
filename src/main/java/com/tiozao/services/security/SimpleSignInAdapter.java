package com.tiozao.services.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Arrays;

public class SimpleSignInAdapter implements SignInAdapter {


    public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
        Connection<Facebook> fbConnection = (Connection<Facebook>) connection;
        Facebook facebook = fbConnection.getApi();
        String[] fields = { "id", "email", "first_name", "last_name" };
        User me = facebook.fetchObject("me", User.class, fields);

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        me.getEmail(), null,
                        Arrays.asList(new SimpleGrantedAuthority("FACEBOOK_USER"))));

        return null;
    }
}
