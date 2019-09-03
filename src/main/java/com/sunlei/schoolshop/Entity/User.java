package com.sunlei.schoolshop.Entity;

import com.sunlei.schoolshop.Annotation.PhoneNum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author 孙磊
 * 用户实体类
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Entity
@ApiModel
public class User implements Serializable{
    @ApiModelProperty(name = "userId", value = "用户Id",example = "1")
    @Id
    @GeneratedValue
    private Integer userId;
    @ApiModelProperty(name = "userHead",value = "我忘了",example = "")
    private String userHead;
    @ApiModelProperty(name = "userName",value = "姓名",example = "孙磊")
    private String userName;
    @ApiModelProperty(name = "userGender",value = "性别",example = "男")
    private String userGender;
    @ApiModelProperty(name = "userCity",value = "城市",example = "天津市")
    private String userCity;
    @ApiModelProperty(name = "userProvince",value = "省份",example = "天津市")
    private String userProvince;
    @ApiModelProperty(name = "userNewLoginTime",value = "最新登陆时间")
    private Timestamp userNewLoginTime;
    @ApiModelProperty(name = "userOpenId",value = "用户openId",example = "12345678")
    private String userOpenId;
    @ApiModelProperty(name = "userCode",value = "前端对用户生成Id",example = "12345567")
    private String userCode;
    @ApiModelProperty(name = "userSessionKey",value = "微信sessionKey",example = "123456677")
    private String userSessionKey;
    @ApiModelProperty(name = "userPassword",value = "用户密码",example = "123asd")
    private String userPassword;
    @ApiModelProperty(name = "userPhoneNum",value = "用户手机号",example = "17694904098")
    private String userPhoneNum;

    public String getUserSessionKey() {
        return userSessionKey;
    }

    public void setUserSessionKey(String userSessionKey) {
        this.userSessionKey = userSessionKey;
    }

    public Timestamp getUserNewLoginTime() {
        return userNewLoginTime;
    }

    public void setUserNewLoginTime(Timestamp userNewLoginTime) {
        this.userNewLoginTime = userNewLoginTime;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserProvince() {
        return userProvince;
    }

    public void setUserProvince(String userProvince) {
        this.userProvince = userProvince;
    }

    public String getUserOpenId() {
        return userOpenId;
    }

    public void setUserOpenId(String userOpenId) {
        this.userOpenId = userOpenId;
    }


}
