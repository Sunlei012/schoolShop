package com.sunlei.schoolshop.Dao;

import com.sunlei.schoolshop.Entity.Orderl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 孙磊
 * 订单Dao类
 */
@Transactional
public interface OrderlDao extends JpaRepository<Orderl,String> {
}
