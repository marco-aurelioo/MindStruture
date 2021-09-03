package com.tiozao.services.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Arrays;

public class SimpleSignInAdapter implements SignInAdapter {


    public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        connection.getKey().getProviderId()+"+"+connection.getKey().getProviderUserId()+"|"+connection.getDisplayName()
                        , null,
                        Arrays.asList(new SimpleGrantedAuthority("FACEBOOK_USER"))));

        return null;
    }
}
