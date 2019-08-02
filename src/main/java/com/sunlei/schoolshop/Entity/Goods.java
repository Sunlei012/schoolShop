package com.sunlei.schoolshop.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author 孙磊
 * 商品实体类
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Entity
public class Goods implements Serializable {
  @Id
  @GeneratedValue
  private Integer goodsId;

  private String goodsTitle;

  private Double goodsPrice;

  private String goodsIntroduction;

  private Integer goodsUserId;

  private Timestamp goodsSetTime;

  private Timestamp goodsUpTime;

  private Integer goodsCategoryId;

  private Boolean isBuy = true;

  public Boolean getBuy() {
    return isBuy;
  }

  public void setBuy(Boolean buy) {
    isBuy = buy;
  }

  public Integer getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(Integer goodsId) {
    this.goodsId = goodsId;
  }

  public String getGoodsTitle() {
    return goodsTitle;
  }

  public void setGoodsTitle(String goodsTitle) {
    this.goodsTitle = goodsTitle;
  }

  public Double getGoodsPrice() {
    return goodsPrice;
  }

  public void setGoodsPrice(Double goodsPrice) {
    this.goodsPrice = goodsPrice;
  }

  public String getGoodsIntroduction() {
    return goodsIntroduction;
  }

  public void setGoodsIntroduction(String goodsIntroduction) {
    this.goodsIntroduction = goodsIntroduction;
  }

  public Integer getGoodsUserId() {
    return goodsUserId;
  }

  public void setGoodsUserId(Integer goodsUserId) {
    this.goodsUserId = goodsUserId;
  }

  public Timestamp getGoodsSetTime() {
    return goodsSetTime;
  }

  public void setGoodsSetTime(Timestamp goodsSetTime) {
    this.goodsSetTime = goodsSetTime;
  }

  public Timestamp getGoodsUpTime() {
    return goodsUpTime;
  }

  public void setGoodsUpTime(Timestamp goodsUpTime) {
    this.goodsUpTime = goodsUpTime;
  }

  public Integer getGoodsCategoryId() {
    return goodsCategoryId;
  }

  public void setGoodsCategoryId(Integer goodsCategoryId) {
    this.goodsCategoryId = goodsCategoryId;
  }
}
