package com.sunlei.schoolshop.Dao;

import com.sunlei.schoolshop.Entity.PhoneNum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PhoneNumDao extends JpaRepository<PhoneNum,String> {
}
