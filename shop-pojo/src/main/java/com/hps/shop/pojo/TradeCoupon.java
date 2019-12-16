package com.hps.shop.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "trade_coupon")
public class TradeCoupon implements Serializable {
    /**
     * 优惠券ID
     */
    @Id
    @Column(name = "coupon_id")
    private Long couponId;

    /**
     * 优惠券金额
     */
    @Column(name = "coupon_price")
    private BigDecimal couponPrice;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 是否使用 0未使用 1已使用
     */
    @Column(name = "is_used")
    private Integer isUsed;

    /**
     * 使用时间
     */
    @Column(name = "used_time")
    private Date usedTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取优惠券ID
     *
     * @return coupon_id - 优惠券ID
     */
    public Long getCouponId() {
        return couponId;
    }

    /**
     * 设置优惠券ID
     *
     * @param couponId 优惠券ID
     */
    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    /**
     * 获取优惠券金额
     *
     * @return coupon_price - 优惠券金额
     */
    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    /**
     * 设置优惠券金额
     *
     * @param couponPrice 优惠券金额
     */
    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取订单ID
     *
     * @return order_id - 订单ID
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置订单ID
     *
     * @param orderId 订单ID
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取是否使用 0未使用 1已使用
     *
     * @return is_used - 是否使用 0未使用 1已使用
     */
    public Integer getIsUsed() {
        return isUsed;
    }

    /**
     * 设置是否使用 0未使用 1已使用
     *
     * @param isUsed 是否使用 0未使用 1已使用
     */
    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }

    /**
     * 获取使用时间
     *
     * @return used_time - 使用时间
     */
    public Date getUsedTime() {
        return usedTime;
    }

    /**
     * 设置使用时间
     *
     * @param usedTime 使用时间
     */
    public void setUsedTime(Date usedTime) {
        this.usedTime = usedTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", couponId=").append(couponId);
        sb.append(", couponPrice=").append(couponPrice);
        sb.append(", userId=").append(userId);
        sb.append(", orderId=").append(orderId);
        sb.append(", isUsed=").append(isUsed);
        sb.append(", usedTime=").append(usedTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}