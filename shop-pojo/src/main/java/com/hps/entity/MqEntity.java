package com.hps.entity;

import java.math.BigDecimal;

/**
 * @author heps
 * @date 2019/12/16 10:19
 */
public class MqEntity {
    private Long orderId;

    private Long couponId;

    private Long userId;

    private BigDecimal userMoney;

    private Long goodsId;

    private Integer goodsNum;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(BigDecimal userMoney) {
        this.userMoney = userMoney;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    @Override
    public String toString() {
        return "MqEntity{" +
                "orderId=" + orderId +
                ", couponId=" + couponId +
                ", userId=" + userId +
                ", userMoney=" + userMoney +
                ", goodsId=" + goodsId +
                ", goodsNum=" + goodsNum +
                '}';
    }
}
