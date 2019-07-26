package com.sunlei.schoolshop.Message.enums;

public enum  LoginErrorCodeAndMsg {
    //不带token登陆
    No_Token_Login("0001","无token请重新登录"),
    //token验证通过，但没有此用户
    No_User_Login("0002","用户不存在，请重新登录"),
    //token验证不通过
    Bad_Token_Login("0003","无效token"),
    //其他问题
    Network_error("9999","网络错误");

    private String code;
    private String msg;

    LoginErrorCodeAndMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }


}
