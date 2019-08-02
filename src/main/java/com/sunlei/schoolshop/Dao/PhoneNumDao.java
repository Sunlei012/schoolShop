package com.sunlei.schoolshop.Dao;

import com.sunlei.schoolshop.Entity.PhoneNum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 孙磊
 * 手机号码登陆验证Dao类
 */
@Transactional
public interface PhoneNumDao extends JpaRepository<PhoneNum,String> {
    PhoneNum findByUserPhoneNum(String userPhoneNum);
}
