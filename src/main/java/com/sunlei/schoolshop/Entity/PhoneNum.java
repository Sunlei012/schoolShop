package com.sunlei.schoolshop.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @author 孙磊
 * 手机号码验证登陆实体类
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Entity
public class PhoneNum {
    @Id
    private String userPhoneNum;
    private String verification;
    private Timestamp sendTime;
}
