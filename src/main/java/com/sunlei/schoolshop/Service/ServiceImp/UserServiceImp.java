package com.sunlei.schoolshop.Service.ServiceImp;

import com.sunlei.schoolshop.Dao.UserDao;
import com.sunlei.schoolshop.Entity.User;
import com.sunlei.schoolshop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    public UserDao userDao;
    @Override
    public boolean isNewUser(User user) {
        return findUserByOpenId(user.getUserOpenId()) != null;
    }

    @Override
    public User findUserByOpenId(String userOpenId) {
        return userDao.findByUserOpenId(userOpenId);
    }

    @Override
    public boolean addUser(User user) {
        return userDao.save(user) != null;

    }

    @Override
    public void updateUserLoginTime(User user) {
        userDao.save(user);

    }

    @Override
    public User updateUser(User user) {
        userDao.save(user);
        return userDao.findByUserOpenId(user.getUserOpenId());
    }

    @Override
    public User loginByPwAndPhoneNum(User user) {
        User loginUser = userDao.findByUserPasswordAndUserPhoneNum(user.getUserPassword(),user.getUserPhoneNum());
        if (loginUser != null){
            Date date = new Date();
            loginUser.setUserNewLoginTime(new Timestamp(date.getTime()));
            updateUserLoginTime(loginUser);
            loginUser.setUserPassword(null);
            return loginUser;
        }else {
            return null;
        }
    }

    @Override
    public User findUserByPhoneNum(String  userPhoneNum) {
        return userDao.findByUserPhoneNum(userPhoneNum);
    }

    @Override
    public User loginByPhoneNum(String userPhoneNum) {

        User user = userDao.findByUserPhoneNum(userPhoneNum);
        User user1 = new User();
        if (user != null){
            Date date = new Date();
            user.setUserNewLoginTime(new Timestamp(date.getTime()));
            updateUserLoginTime(user);
        }else {

            user1.setUserPhoneNum(userPhoneNum);
            Date date = new Date();
            user1.setUserNewLoginTime(new Timestamp(date.getTime()));
            updateUserLoginTime(user1);
        }
        return userDao.findByUserPhoneNum(userPhoneNum);
    }
}
