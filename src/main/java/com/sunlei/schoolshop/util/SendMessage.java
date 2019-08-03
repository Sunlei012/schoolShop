package com.sunlei.schoolshop.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import com.sunlei.schoolshop.Config.SendMessageStaticParam;
import com.sunlei.schoolshop.Dao.PhoneNumDao;
import com.sunlei.schoolshop.Dao.UserDao;
import com.sunlei.schoolshop.Entity.PhoneNum;
import com.sunlei.schoolshop.Message.Response;
import com.sunlei.schoolshop.Message.enums.CommErrorCodeAndMsg;
import com.sunlei.schoolshop.Message.enums.LoginErrorCodeAndMsg;
import com.sunlei.schoolshop.Message.exception.CommException;
import com.sunlei.schoolshop.Message.exception.LoginException;
import com.sunlei.schoolshop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

/**
 * @author 孙磊
 * 手机验证码发送工具类
 */
public class SendMessage {
    private static String code;
//    public static void main(String[] args) {
//        String phone = "13072019850";
//        //此处可输入你的手机号码进行测试
//        getPhonemsg(phone);
//12
//    }
    public static Response<String> getPhonemsg(String mobile, PhoneNumDao phoneNumDao, UserService userService){
        /**
         * 进行正则关系校验
         */
        System.out.println(mobile);
        if (mobile == null || mobile == "") {
            System.out.println("手机号为空");
            Response<String> rent = new Response<String>();
            rent.setCode("0001");
            rent.setMsg("手机号为空");
            return rent;
        }
        /**
         * 短信验证---阿里大于工具
         */

        // 设置超时时间-可自行调整
        System.setProperty(SendMessageStaticParam.DEFAULT_CONNECT_TIMEOUT, SendMessageStaticParam.TIMEOUT);
        System.setProperty(SendMessageStaticParam.DEFAULT_READ_TIMEOUT, SendMessageStaticParam.TIMEOUT);
        // 初始化ascClient需要的几个参数
        final String product = SendMessageStaticParam.PRODUCT;// 短信API产品名称（短信产品名固定，无需修改）
        final String domain = SendMessageStaticParam.DOMAIN;// 短信API产品域名（接口地址固定，无需修改）
        // 替换成你的AK
        final String accessKeyId = SendMessageStaticParam.ACCESS_KEY_ID;// 你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = SendMessageStaticParam.ACCESS_KEY_SECRET;// 你的accessKeySecret，参考本文档步骤2
        // 初始化ascClient,暂时不支持多region
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                accessKeyId, accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product,
                    domain);
        } catch (ClientException e1) {
            e1.printStackTrace();
        }

        //获取验证码
        code = vcode();

        IAcsClient client = new DefaultAcsClient(profile);
        // 组装请求对象
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(domain);
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName",SendMessageStaticParam.SIGN_NAME );
        request.putQueryParameter("TemplateCode", SendMessageStaticParam.TEMPLATE_CODE);
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        Response<String> rent = new Response<>();
        try {
            CommonResponse response = client.getCommonResponse(request);
            //JSONObject date = JSON.parseObject(response.getData());
//            if ("OK".equals(date.getString("Code"))&&
//             date.isEmpty()){
//                System.out.println(date.getDate("Message")+"请求成功！");
                rent.setData(code);
                //response.getData();
                //HashMap j = new HashMap();
                //j = (HashMap) JSONObject.parseObject(response.getData()).get("Code");
                if ("OK".equals(JSONObject.parseObject(response.getData()).get("Code"))) {
                    System.out.println();
                    PhoneNum phoneNum = new PhoneNum();
                    phoneNum.setUserPhoneNum(mobile);
                    phoneNum.setVerification(code);
                    Date date1 = new Date();
                    phoneNum.setSendTime(new Timestamp(date1.getTime()));
                    phoneNumDao.save(phoneNum);
//                System.out.println(rent);
                    return rent;
                }
            else {
                throw new CommException(CommErrorCodeAndMsg.Network_error);
                }
//            }else {
//            System.out.println(date.getDate("Message")+"失败！"+date.getDate("Code"));
//            rent.setMsg(date.getString("Message"));
//            rent.setCode(date.getString("Code"));
//            throw new CommException(rent);
//            }

        } catch (ServerException e) {
            throw new CommException(CommErrorCodeAndMsg.Network_error);
        } catch (ClientException e) {
            e.printStackTrace();
            throw new CommException(CommErrorCodeAndMsg.Network_error);
        }
        //        try {
//            sendSmsResponse = acsClient.getAcsResponse(request);
//            if (sendSmsResponse.getCode() != null
//                    && sendSmsResponse.getCode().equals("OK")) {
//                // 请求成功
//                System.out.println("获取验证码成功！！！");
//            } else {                               
//                                //如果验证码出错，会输出错误码告诉你具体原因
//                                System.out.println(sendSmsResponse.getCode());
//                                System.out.println("获取验证码失败...");
//            }
//        } catch (ServerException e) {
//            e.printStackTrace();
//            return "由于系统维护，暂时无法注册！！！";
//        } catch (ClientException e) {
//            e.printStackTrace();
//            return "由于系统维护，暂时无法注册！！！";
//        }
//        return "true";

    }

    /**
     * 生成6位随机数验证码
     * @return
     */
    private static String vcode(){
        StringBuilder vcode = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            vcode.append((int) (Math.random() * 9));
        }
        return vcode.toString();
    }

}

