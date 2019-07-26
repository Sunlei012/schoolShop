package com.sunlei.schoolshop.Message.exception;

import com.sunlei.schoolshop.Message.Response;
import com.sunlei.schoolshop.Message.enums.LoginErrorCodeAndMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(LoginException.class)
    @ResponseBody
    public Response handleStudentException(HttpServletRequest request, LoginException ex) {
        Response response;
        log.error("StudentException code:{},msg:{}",ex.getResponse().getCode(),ex.getResponse().getMsg());
        response = new Response(ex.getResponse().getCode(),ex.getResponse().getMsg());
        return response;
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseBody
    public Response handleException(HttpServletRequest request, Exception ex) {
        Response response;
        log.error("exception error:{}",ex);
        response = new Response(LoginErrorCodeAndMsg.Network_error.getCode(),
                LoginErrorCodeAndMsg.Network_error.getMsg());
        return response;
    }


}
