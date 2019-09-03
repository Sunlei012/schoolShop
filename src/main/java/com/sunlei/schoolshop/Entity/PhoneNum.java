package com.sunlei.schoolshop.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
@Entity
public class PhoneNum {
    @Id
    @ApiModelProperty(name = "userPhoneNum",value = "用户手机号",example = "17694904098")
    private String userPhoneNum;
    @ApiModelProperty(name = "verification",value = "验证码",example = "123456")
    private String verification;
}
