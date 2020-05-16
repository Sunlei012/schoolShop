package com.sunlei.schoolshop.Config;

/**
 * @author 孙磊
 * 阿里云手机验证码静态参数
 */
public class SendMessageStaticParam {
    /**
     * 手机验证部分配置
     */
    // 设置超时时间-可自行调整
    public final static String DEFAULT_CONNECT_TIMEOUT = "sun.net.client.DEFAULT_CONNECT_TIMEOUT";
    public final static String DEFAULT_READ_TIMEOUT = "sun.net.client.DEFAULT_READ_TIMEOUT";
    public final static String TIMEOUT = "10000";
    // 初始化ascClient需要的几个参数
    public final static String PRODUCT = "Dysmsapi";
    // 短信API产品名称（短信产品名固定，无需修改）
    public final static String DOMAIN = "dysmsapi.aliyuncs.com";
    // 短信API产品域名（接口地址固定，无需修改）

    // 替换成你的AK (产品密)
    public final static String ACCESS_KEY_ID = "B";
    // 你的accessKeyId,填你自己的 上文配置所得  自行配置
    public final static String ACCESS_KEY_SECRET = "";
    // 你的accessKeySecret,填你自己的 上文配置所得 自行配置
    // 必填:短信签名-可在短信控制台中找到
    public final static String SIGN_NAME = "校园卖";
    // 阿里云配置你自己的短信签名填入
    // 必填:短信模板-可在短信控制台中找到
    public final static String TEMPLATE_CODE = "SMS_171750206";
    // 阿里云配置你自己的短信模板填入
}
