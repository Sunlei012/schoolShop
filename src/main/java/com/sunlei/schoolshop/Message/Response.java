package com.sunlei.schoolshop.Message;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author 孙磊
 * 统一回调参数实体类
 */
@Getter
@Setter
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 2662698163826545810L;

    private static final String SUCCESS_CODE = "0000";

    /**
     * 返回数据
     */
    private T data;

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回描述
     */
    private String msg;

    public Response(){
        this.code = SUCCESS_CODE;
        this.msg = "请求成功";
    }

    public Response(String code,String msg){
        this();
        this.code = code;
        this.msg = msg;
    }
    public Response(String code,String msg,T data){
        this();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public Response(T data){
        this();
        this.data = data;
    }
}
