package com.hps.shop.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "trade_pay")
public class TradePay implements Serializable {
    /**
     * 支付编号
     */
    @Id
    @Column(name = "pay_id")
    private Long payId;

    /**
     * 订单编号
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 支付金额
     */
    @Column(name = "pay_amount")
    private BigDecimal payAmount;

    /**
     * 是否已支付 1否 2是
     */
    @Column(name = "is_paid")
    private Integer isPaid;

    private static final long serialVersionUID = 1L;

    /**
     * 获取支付编号
     *
     * @return pay_id - 支付编号
     */
    public Long getPayId() {
        return payId;
    }

    /**
     * 设置支付编号
     *
     * @param payId 支付编号
     */
    public void setPayId(Long payId) {
        this.payId = payId;
    }

    /**
     * 获取订单编号
     *
     * @return order_id - 订单编号
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置订单编号
     *
     * @param orderId 订单编号
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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
     * 获取是否已支付 1否 2是
     *
     * @return is_paid - 是否已支付 1否 2是
     */
    public Integer getIsPaid() {
        return isPaid;
    }

    /**
     * 设置是否已支付 1否 2是
     *
     * @param isPaid 是否已支付 1否 2是
     */
    public void setIsPaid(Integer isPaid) {
        this.isPaid = isPaid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", payId=").append(payId);
        sb.append(", orderId=").append(orderId);
        sb.append(", payAmount=").append(payAmount);
        sb.append(", isPaid=").append(isPaid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}