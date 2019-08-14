package com.tiozao.exception;

public class UserSessionNotFound extends RuntimeException {

    public UserSessionNotFound(String mensage){
        super(mensage);
    }

}
