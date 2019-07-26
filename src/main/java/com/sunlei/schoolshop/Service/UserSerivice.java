package com.sunlei.schoolshop.Service;

import com.sunlei.schoolshop.Entity.User;

public interface UserSerivice {
    public boolean isNewUser(User user);
    public User findUserByOpenId(String userOpenId);
    public boolean addUser(User user);
    public void updateUserLoginTime(User user);
    public User updateUser(User user);
    public User loginByPwAndPhoneNum(User user);
    public User findUserByPhoneNum(String  userPhoneNum);
}
