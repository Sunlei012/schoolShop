package com.sunlei.schoolshop.Message.exception;


import com.sunlei.schoolshop.Message.Response;
import com.sunlei.schoolshop.Message.enums.CommErrorCodeAndMsg;

public class BindException extends RuntimeException  {

    private static final long serialVersionUID = 2688710331604542883L;
    private CommErrorCodeAndMsg response = null;
    private Response res = null;
    public BindException(CommErrorCodeAndMsg response) {
        this.response = response;
    }
    public BindException(Response res){
        this.res = res;
    }
    public BindException(Response res,CommErrorCodeAndMsg response){
        this.res = res;
        this.response = response;
    }
    public CommErrorCodeAndMsg getResponse(){
        return response;
    }
}
