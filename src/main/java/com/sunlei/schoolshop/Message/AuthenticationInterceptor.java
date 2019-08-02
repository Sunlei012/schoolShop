package com.sunlei.schoolshop.Message;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.sunlei.schoolshop.Annotation.PassToken;
import com.sunlei.schoolshop.Annotation.UserLoginToken;
import com.sunlei.schoolshop.Entity.User;
import com.sunlei.schoolshop.Message.enums.LoginErrorCodeAndMsg;
import com.sunlei.schoolshop.Message.exception.LoginException;
import com.sunlei.schoolshop.Service.ServiceImp.UserServiceImpl;
import com.sunlei.schoolshop.util.JwtTkoen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author 孙磊
 * 登陆校验拦截器
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getParameter("token");

        // 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    //throw new RuntimeException("无token，请重新登录");
                    throw new LoginException(LoginErrorCodeAndMsg.No_Token_Login);
                }
                // 获取 token 中的 user id
                String userPhoneNum;
                User user = new User();
                try {
                    //userPhoneNum = JWT.decode(token).getAudience().get(0);
                    HashMap result = new HashMap(3);
                    result = new JwtTkoen().getAppUID(token, userServiceImpl);
                    user = (User) result.get("Date");
                    userPhoneNum = user.getUserPhoneNum();
                    user.setUserPhoneNum(userPhoneNum);
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("500");
                }
                user = userServiceImpl.findUserByPhoneNum(user.getUserPhoneNum());
                if (user == null) {
                    //throw new RuntimeException("用户不存在，请重新登录");
                    throw new LoginException(LoginErrorCodeAndMsg.No_User_Login);
                }
                // 验证 token
                //JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                try {
                    JwtTkoen.verifyToken(token);
                    //jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    //throw new RuntimeException("401");
                    throw new LoginException(LoginErrorCodeAndMsg.Network_error);
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
