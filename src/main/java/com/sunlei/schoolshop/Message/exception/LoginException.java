package com.sunlei.schoolshop.Message.exception;

import com.sunlei.schoolshop.Message.enums.LoginErrorCodeAndMsg;

public class LoginException extends RuntimeException {
    private static final long serialVersionUID = -6228072121157757067L;
    private final LoginErrorCodeAndMsg response;

    public LoginException(LoginErrorCodeAndMsg response) {
        this.response = response;
    }
    public LoginErrorCodeAndMsg getResponse(){
        return response;
    }
}
