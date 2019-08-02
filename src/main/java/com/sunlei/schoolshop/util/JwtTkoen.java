package com.sunlei.schoolshop.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sunlei.schoolshop.Entity.User;

import com.sunlei.schoolshop.Message.Response;
import com.sunlei.schoolshop.Message.enums.LoginErrorCodeAndMsg;
import com.sunlei.schoolshop.Message.exception.LoginException;
import com.sunlei.schoolshop.Service.UserService;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 孙磊
 * Jwt验证工具类
 */
public class JwtTkoen {
    /** token秘钥，请勿泄露，请勿随便修改 backups:JKKLJOoasdlfj */
    private static final String SECRET = "JKKLJOoasdlfj";
    /** token 过期时间: 10天 */
    private static final int CALENDAR_FIELD = Calendar.DATE;
    private static final int CALENDAR_INTERVAL = 10;
    //@Autowired
    //private static UserService userService;
    /**
     * JWT生成Token.<br/>
     *
     * JWT构成: header, payload, signature
     *
     * @param user
     *            登录成功后用户user_id, 参数user_id不可传空
     */
    public static String createToken(User user) throws Exception {
        Date iatDate = new Date();
        // expire time
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(CALENDAR_FIELD, CALENDAR_INTERVAL);
        Date expiresDate = nowTime.getTime();

        // header Map
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        // build token
        // param backups {iss:Service, aud:APP}
        // signature

        return JWT.create().withHeader(map)
                // header
                .withClaim("iss", "Service")
                // payload
                .withClaim("aud", "APP").withClaim("userPhoneNum", user.getUserPhoneNum())
                .withIssuedAt(iatDate)
                // sign time
                .withExpiresAt(expiresDate)
                // expire time
                .sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * 解密Token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
             e.printStackTrace();
            // token 校验失败, 抛出Token验证非法异常
        }
        assert jwt != null;
        return jwt.getClaims();
    }

    /**
     * 根据Token获取user_id
     *
     * @param token
     * @return user_id
     */
    public static HashMap<String, java.io.Serializable> getAppUID(String token, UserService userService) {
        //UserServiceImp userServiceImp = new UserServiceImp();
        Map<String, Claim> claims = verifyToken(token);
        HashMap<String, java.io.Serializable> result = new HashMap<>(3);
        //State state = new State();
        Claim userPhoneNum = claims.get("userPhoneNum");
        if (null == userPhoneNum || StringUtils.isEmpty(userPhoneNum)) {
//            state.setRequestNum(401);
//            state.setRequestDetail("验证失败");
//            result.put("Date",null);
//            result.put("RequestNum",state.getRequestNum());
//            result.put("RequestDetail",state.getRequestDetail());
            throw new LoginException(LoginErrorCodeAndMsg.Bad_Token_Login);

        }else {
            User user = new User();
            user.setUserPhoneNum(userPhoneNum.asString());
            //state.setRequestNum(200);
            //state.setRequestDetail("验证成功");
            user = userService.findUserByPhoneNum(user.getUserPhoneNum());
            Response<User> response = new Response<>(user);
            result.put("RequestNum",response.getCode());
            result.put("RequestDetail",response.getMsg());
            result.put("Date",response.getData());
            return result;
        }

    }
}
