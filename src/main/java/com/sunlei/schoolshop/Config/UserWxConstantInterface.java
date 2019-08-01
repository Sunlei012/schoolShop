package com.sunlei.schoolshop.Config;

public interface UserWxConstantInterface {
    String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";
    // 你的appid
    String WX_LOGIN_APPID = "xxxxxxxxxxxxxxxxxx";
    // 你的密匙
    String WX_LOGIN_SECRET = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
    // 固定参数
    String WX_LOGIN_GRANT_TYPE = "authorization_code";

}
