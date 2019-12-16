package com.hps.shop.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "trade_order")
public class TradeOrder implements Serializable {
    /**
     * 订单ID
     */
    @Id
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 订单状态 0未确认 1已确认 2已取消 3无效 4退款
     */
    @Column(name = "order_status")
    private Integer orderStatus;

    /**
     * 支付状态 0未支付 1支付中 2已支付
     */
    @Column(name = "pay_status")
    private Integer payStatus;

    /**
     * 发货状态 0未发货 1已发货 2已退货
     */
    @Column(name = "shipping_status")
    private Integer shippingStatus;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 商品ID
     */
    @Column(name = "goods_id")
    private Long goodsId;

    /**
     * 商品库存
     */
    @Column(name = "goods_number")
    private Integer goodsNumber;

    /**
     * 商品价格
     */
    @Column(name = "goods_price")
    private BigDecimal goodsPrice;

    /**
     * 商品总价
     */
    @Column(name = "goods_amount")
    private BigDecimal goodsAmount;

    /**
     * 运费
     */
    @Column(name = "shipping_fee")
    private BigDecimal shippingFee;

    /**
     * 订单价格
     */
    @Column(name = "order_amount")
    private BigDecimal orderAmount;

    /**
     * 优惠券ID
     */
    @Column(name = "coupon_id")
    private Long couponId;

    /**
     * 优惠券
     */
    @Column(name = "coupon_paid")
    private BigDecimal couponPaid;

    /**
     * 已付金额
     */
    @Column(name = "money_paid")
    private BigDecimal moneyPaid;

    /**
     * 支付金额
     */
    @Column(name = "pay_amount")
    private BigDecimal payAmount;

    /**
     * 创建时间
     */
    @Column(name = "add_time")
    private Date addTime;

    /**
     * 确认时间
     */
    @Column(name = "confirm_time")
    private Date confirmTime;

    /**
     * 支付时间
     */
    @Column(name = "pay_time")
    private Date payTime;

    private static final long serialVersionUID = 1L;

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
     * 获取订单状态 0未确认 1已确认 2已取消 3无效 4退款
     *
     * @return order_status - 订单状态 0未确认 1已确认 2已取消 3无效 4退款
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置订单状态 0未确认 1已确认 2已取消 3无效 4退款
     *
     * @param orderStatus 订单状态 0未确认 1已确认 2已取消 3无效 4退款
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 获取支付状态 0未支付 1支付中 2已支付
     *
     * @return pay_status - 支付状态 0未支付 1支付中 2已支付
     */
    public Integer getPayStatus() {
        return payStatus;
    }

    /**
     * 设置支付状态 0未支付 1支付中 2已支付
     *
     * @param payStatus 支付状态 0未支付 1支付中 2已支付
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * 获取发货状态 0未发货 1已发货 2已退货
     *
     * @return shipping_status - 发货状态 0未发货 1已发货 2已退货
     */
    public Integer getShippingStatus() {
        return shippingStatus;
    }

    /**
     * 设置发货状态 0未发货 1已发货 2已退货
     *
     * @param shippingStatus 发货状态 0未发货 1已发货 2已退货
     */
    public void setShippingStatus(Integer shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    /**
     * 获取收货地址
     *
     * @return address - 收货地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置收货地址
     *
     * @param address 收货地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取收货人
     *
     * @return consignee - 收货人
     */
    public String getConsignee() {
        return consignee;
    }

    /**
     * 设置收货人
     *
     * @param consignee 收货人
     */
    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    /**
     * 获取商品ID
     *
     * @return goods_id - 商品ID
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 设置商品ID
     *
     * @param goodsId 商品ID
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取商品库存
     *
     * @return goods_number - 商品库存
     */
    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    /**
     * 设置商品库存
     *
     * @param goodsNumber 商品库存
     */
    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    /**
     * 获取商品价格
     *
     * @return goods_price - 商品价格
     */
    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    /**
     * 设置商品价格
     *
     * @param goodsPrice 商品价格
     */
    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    /**
     * 获取商品总价
     *
     * @return goods_amount - 商品总价
     */
    public BigDecimal getGoodsAmount() {
        return goodsAmount;
    }

    /**
     * 设置商品总价
     *
     * @param goodsAmount 商品总价
     */
    public void setGoodsAmount(BigDecimal goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    /**
     * 获取运费
     *
     * @return shipping_fee - 运费
     */
    public BigDecimal getShippingFee() {
        return shippingFee;
    }

    /**
     * 设置运费
     *
     * @param shippingFee 运费
     */
    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }

    /**
     * 获取订单价格
     *
     * @return order_amount - 订单价格
     */
    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    /**
     * 设置订单价格
     *
     * @param orderAmount 订单价格
     */
    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

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
     * 获取优惠券
     *
     * @return coupon_paid - 优惠券
     */
    public BigDecimal getCouponPaid() {
        return couponPaid;
    }

    /**
     * 设置优惠券
     *
     * @param couponPaid 优惠券
     */
    public void setCouponPaid(BigDecimal couponPaid) {
        this.couponPaid = couponPaid;
    }

    /**
     * 获取已付金额
     *
     * @return money_paid - 已付金额
     */
    public BigDecimal getMoneyPaid() {
        return moneyPaid;
    }

    /**
     * 设置已付金额
     *
     * @param moneyPaid 已付金额
     */
    public void setMoneyPaid(BigDecimal moneyPaid) {
        this.moneyPaid = moneyPaid;
    }

    /**
     * 获取支付金额
     *
     * @return pay_amount - 支付金额
     */
    public BigDecimal getPayAmount() {
        return payAmount;
    }

    /**
     * 设置支付金额
     *
     * @param payAmount 支付金额
     */
    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * 获取创建时间
     *
     * @return add_time - 创建时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 设置创建时间
     *
     * @param addTime 创建时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取确认时间
     *
     * @return confirm_time - 确认时间
     */
    public Date getConfirmTime() {
        return confirmTime;
    }

    /**
     * 设置确认时间
     *
     * @param confirmTime 确认时间
     */
    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    /**
     * 获取支付时间
     *
     * @return pay_time - 支付时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * 设置支付时间
     *
     * @param payTime 支付时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderId=").append(orderId);
        sb.append(", userId=").append(userId);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", payStatus=").append(payStatus);
        sb.append(", shippingStatus=").append(shippingStatus);
        sb.append(", address=").append(address);
        sb.append(", consignee=").append(consignee);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsNumber=").append(goodsNumber);
        sb.append(", goodsPrice=").append(goodsPrice);
        sb.append(", goodsAmount=").append(goodsAmount);
        sb.append(", shippingFee=").append(shippingFee);
        sb.append(", orderAmount=").append(orderAmount);
        sb.append(", couponId=").append(couponId);
        sb.append(", couponPaid=").append(couponPaid);
        sb.append(", moneyPaid=").append(moneyPaid);
        sb.append(", payAmount=").append(payAmount);
        sb.append(", addTime=").append(addTime);
        sb.append(", confirmTime=").append(confirmTime);
        sb.append(", payTime=").append(payTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}