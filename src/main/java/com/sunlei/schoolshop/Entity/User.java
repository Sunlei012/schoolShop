package com.sunlei.schoolshop.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.sql.Timestamp;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@Entity
public class User implements Serializable{
    @Id
    @GeneratedValue
    private Integer userId;

    private String userHead;

    private String userName;

    private String userGender;

    private String userCity;

    private String userProvince;

    private Timestamp userNewLoginTime;

    private String userOpenId;

    private String userCode;

    private String userSessionKey;

    private String userPassword;

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
