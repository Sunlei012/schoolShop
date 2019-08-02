package com.sunlei.schoolshop.Entity;

import com.sunlei.schoolshop.util.KeyGenerate;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @author 孙磊
 * 订单实体类
 */
@Data
@Entity
public class Orderl {
    @Id
    private String orderId;
    private Integer orderUserId;
    private Integer orderGoodsId;
    private Byte orderState;
    private Timestamp orderStartTime;
    private Timestamp orderNowTime;

    public void setOrderId(){
        this.orderId = KeyGenerate.nextId();
    }

}
