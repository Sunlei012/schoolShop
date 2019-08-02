package com.sunlei.schoolshop.Service;

import com.sunlei.schoolshop.Entity.User;
import org.springframework.stereotype.Service;

/**
 * @author 孙磊
 * 用户Service接口类
 */
@Service
public interface UserService {
    boolean isNewUser(User user);
    User findUserByOpenId(String userOpenId);
    boolean addUser(User user);
    void updateUserLoginTime(User user);
    User updateUser(User user);
    User loginByPwAndPhoneNum(User user);
    User findUserByPhoneNum(String userPhoneNum);
    User loginByPhoneNum(String userPhoneNum);
}
