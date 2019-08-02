package com.sunlei.schoolshop.Config;

/**
 * @author 孙磊
 * 微信登陆静态参数
 */
public interface UserWxConstantInterface {
    String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";

    /**
     * 你的appId
     */
    String WX_LOGIN_APPID = "xxxxxxxxxxxxxxxxxx";

    /**
     * 你的密钥
     */
    String WX_LOGIN_SECRET = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";

    /**
     * 固定参数
     */
    String WX_LOGIN_GRANT_TYPE = "authorization_code";

}
