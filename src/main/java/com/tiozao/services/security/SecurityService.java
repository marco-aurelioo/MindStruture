package com.tiozao.services.security;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
