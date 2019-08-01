package com.sunlei.schoolshop.Message.exception;

import com.sunlei.schoolshop.Message.Response;
import com.sunlei.schoolshop.Message.enums.CommErrorCodeAndMsg;
import com.sunlei.schoolshop.Message.enums.LoginErrorCodeAndMsg;

public class CommException extends RuntimeException  {

    private static final long serialVersionUID = 2688710331604542883L;
    private CommErrorCodeAndMsg response = null;
    private  Response res = null;
    public CommException(CommErrorCodeAndMsg response) {
        this.response = response;
    }
    public CommException(Response res){
        this.res = res;
    }
    public CommException(Response res,CommErrorCodeAndMsg response){
        this.res = res;
        this.response = response;
    }
    public CommErrorCodeAndMsg getResponse(){
        return response;
    }
}
