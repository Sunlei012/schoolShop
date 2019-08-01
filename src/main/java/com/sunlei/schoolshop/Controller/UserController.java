package com.sunlei.schoolshop.Controller;

import com.alibaba.fastjson.JSONObject;
import com.sunlei.schoolshop.Annotation.UserLoginToken;
import com.sunlei.schoolshop.Dao.PhoneNumDao;
import com.sunlei.schoolshop.Entity.PhoneNum;
import com.sunlei.schoolshop.Entity.User;
import com.sunlei.schoolshop.Message.Response;
import com.sunlei.schoolshop.Message.enums.LoginErrorCodeAndMsg;
import com.sunlei.schoolshop.Message.exception.LoginException;
import com.sunlei.schoolshop.Service.*;
import com.sunlei.schoolshop.util.HttpClientUtil;
import com.sunlei.schoolshop.util.JwtTkoen;
import com.sunlei.schoolshop.Config.UserWxConstantInterface;
import com.sunlei.schoolshop.util.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSON;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/User")
public class UserController {
    @Autowired
    private UserService userService;
@Autowired
private PhoneNumDao phoneNumDao;

    @PostMapping(value = "/WXLogin")
    public Response<String> WXLoginUser(@RequestBody User user){
        // 配置请求参数
        String s;
        //State state = new State();
        Map<String, String> param = new HashMap<>();
        param.put("appid", UserWxConstantInterface.WX_LOGIN_APPID);
        param.put("secret", UserWxConstantInterface.WX_LOGIN_SECRET);
        param.put("js_code", user.getUserCode());
        param.put("grant_type", UserWxConstantInterface.WX_LOGIN_GRANT_TYPE);
        // 发送请求
        String wxResult = HttpClientUtil.doGet(UserWxConstantInterface.WX_LOGIN_URL, param);
        JSONObject jsonObject = JSONObject.parseObject(wxResult);
        // 获取参数返回的
        String session_key = jsonObject.get("session_key").toString();
        String open_id = jsonObject.get("openid").toString();
        // 根据返回的user实体类，判断用户是否是新用户，不是的话，更新最新登录时间，是的话，将用户信息存到数据库
        user.setUserOpenId(open_id);
        user.setUserSessionKey(session_key);
        Map<String, String> result = new HashMap<>();
        result.put("session_key", session_key);
        result.put("open_id", open_id);
        if (userService.isNewUser(user)){
            Date date = new Date();
            user.setUserNewLoginTime(new Timestamp(date.getTime()));
            userService.updateUserLoginTime(user);
        }else {
            if (!userService.addUser(user)) {
//                state.setResultNum(401);
//                state.setResultDetail("登陆失败");
//                Response response = new Response();
//                result.put("resultNum", String.valueOf(state.getResultNum()));
//                result.put("resultDetail",state.getResultDetail());
//                s = JSON.toJSONString(result);
                throw new LoginException(LoginErrorCodeAndMsg.Network_error);
            }
        }
        // 封装返回小程序
//        state.setResultNum(200);
//        state.setResultDetail("登陆成功");

//        result.put("resultNum", String.valueOf(state.getResultNum()));
//        result.put("resultDetail",state.getResultDetail());
        try {
            result.put("Token", JwtTkoen.createToken(user));
            Response<String> response = new Response<String>(JSON.toJSONString(result));
            return response;
        } catch (Exception e) {
            //e.printStackTrace();
            throw new LoginException(LoginErrorCodeAndMsg.Network_error);
        }
    }

    @GetMapping(value = "/show/one")
    public Response<User> UserShowOne(@RequestParam(name = "userOpenId") String userOpenId){
        User user = userService.findUserByOpenId(userOpenId);
        return new Response<>(user);
    }

    @PostMapping(value = "/loginByPw")
    public Response<String> LoginUserByPw(@RequestParam(name = "userPassword")String userPassword,
                                          @RequestParam(name = "userPhoneNum")String userPhoneNum){
        User user = new User();
        //State state = new State();
        HashMap<String, java.io.Serializable> result = new HashMap<>(2);
        user.setUserPassword(userPassword);
        user.setUserPhoneNum(userPhoneNum);
        user = userService.loginByPwAndPhoneNum(user);
//        state.setResultNum(401);
//        state.setResultDetail("登陆失败 FAIL!");
        if (user.getUserId() != null){
//            state.setResultDetail("登陆成功 SUCCESS");
//            state.setResultNum(200);
            String token = null;

            try {
                token = JwtTkoen.createToken(user);
            } catch (Exception e) {
                throw new LoginException(LoginErrorCodeAndMsg.Network_error);
            }
//            result.put("ResultDetail",state.getResultDetail());
//            result.put("ResultNum",state.getResultNum());
            result.put("User", user);
            result.put("Token",token);
            Response<String> response = new Response<>(JSON.toJSONString(result));
            return response;
        }else {
//        result.put("ResultNum",state.getResultNum() );
//        result.put("ResultDetail",state.getResultDetail());
            Response<String> response = new Response<>();
            response.setCode(LoginErrorCodeAndMsg.No_User_Login.getCode());
            response.setMsg(LoginErrorCodeAndMsg.No_User_Login.getMsg());
        //String s = JSON.toJSONString(result);
        return response;
        }
    }

    @PostMapping(value = "/phoneVerification")
    public Response<String> PhoneVerificationLogin(@RequestParam(name = "userPhoneNum")String userPhoneNum){
        Response<String> response =  SendMessage.getPhonemsg(userPhoneNum,phoneNumDao,userService);
        return response;
    }
@PostMapping(value = "/phoneLogin")
public Response<String> PhoneLogin(@RequestParam(name = "userPhoneNum")String userPhoneNum, @RequestParam(name = "ver")String ver){
    PhoneNum phoneNum = new PhoneNum();
    phoneNum = phoneNumDao.findByUserPhoneNum(userPhoneNum);
    long nowdate = System.currentTimeMillis();
    Response<String> response = new Response<>();
//    Timestamp nowTime = new Timestamp(date.getTime());
    long date =  phoneNum.getSendTime().getTime();
    if ((phoneNum.getVerification().equals(ver)) &&((nowdate-date) <= 5*1000*24000)){
       User user = new User();
        user = userService.loginByPhoneNum(userPhoneNum);
        HashMap<String, User> result = new HashMap<>(1);
        result.put("User",user);
        response.setData(JSON.toJSONString(result));
        return response;
    }else {
        response.setCode("0001");
        response.setMsg("登陆失败");
        return response;
    }
}
    @UserLoginToken
    @GetMapping(value = "/message")
    public String message(){
        return "tongguo";
    }
    @PostMapping(value = "/registe")
    public String ResgisteUserByPw(){
        return null;
    }

    @GetMapping(value = "/a")
    public String aa(){
        User user = new User();
        user.setUserPhoneNum("13072019850");
        user = userService.findUserByPhoneNum(user.getUserPhoneNum());
        HashMap<String, User> r = new HashMap<>();
        r.put("User",user);
        return r.get("User").toString();
    }
    @GetMapping(value = "/b")
    public String bb(@RequestParam(name = "token") String token){
        User user = new User();
        String userPhoneNum;
        user.setUserPhoneNum("13072019850");
        HashMap result = new HashMap(3);
        result = JwtTkoen.getAppUID(token, userService);
        user = (User) result.get("User");
        userPhoneNum = user.getUserPhoneNum();
        user.setUserPhoneNum(userPhoneNum);
        user = userService.findUserByPhoneNum(user.getUserPhoneNum());
        return user.toString();
    }

    public static int daysBetween2(Date startTime, Date endTime) {
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH");
              Calendar cal = Calendar.getInstance();
              long time1 = 0;
              long time2 = 0;

                  try{
                        cal.setTime(sdf.parse(String.valueOf(startTime)));
                        time1 = cal.getTimeInMillis();
                           cal.setTime(sdf.parse(String.valueOf(endTime)));
                         time2 = cal.getTimeInMillis();
                     }catch(Exception e){
//                      Response response = new Response();
//                      response.setMsg("验证码超时");
//                      response.setCode("0009");
//                      throw new CommException(response);
                      e.printStackTrace();

                  }
                long between_days=(time2-time1)/(1000*60);

              return Integer.parseInt(String.valueOf(between_days));
            }

}
