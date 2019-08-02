package com.sunlei.schoolshop.Dao;

import com.sunlei.schoolshop.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 孙磊
 * 用户Dao类
 */
@Transactional
public interface UserDao extends JpaRepository<User,Integer> {

    User findByUserOpenId(String userOpenId);
    User findByUserPasswordAndUserPhoneNum(String userPassword, String userPhoneNum);
    User findByUserPhoneNum(String userPhoneNum);
}
