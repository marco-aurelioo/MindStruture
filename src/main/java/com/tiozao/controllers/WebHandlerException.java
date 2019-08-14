package com.tiozao.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebHandlerException extends HandlerExceptionResolverComposite {

    @ExceptionHandler(RuntimeException.class)
    public String processRuntimeException(final HttpServletRequest req, final RuntimeException ex) {
        return "error";
    }


}
