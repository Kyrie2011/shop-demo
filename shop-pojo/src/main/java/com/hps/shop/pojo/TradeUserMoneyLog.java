package com.hps.shop.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "trade_user_money_log")
public class TradeUserMoneyLog implements Serializable {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 用户Id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 日志类型 1订单付款 2订单退款
     */
    @Column(name = "money_log_type")
    private Integer moneyLogType;

    /**
     * 操作金额
     */
    @Column(name = "use_money")
    private BigDecimal useMoney;

    /**
     * 日志时间
     */
    @Column(name = "create_time")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户Id
     *
     * @return user_id - 用户Id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户Id
     *
     * @param userId 用户Id
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
     * 获取日志类型 1订单付款 2订单退款
     *
     * @return money_log_type - 日志类型 1订单付款 2订单退款
     */
    public Integer getMoneyLogType() {
        return moneyLogType;
    }

    /**
     * 设置日志类型 1订单付款 2订单退款
     *
     * @param moneyLogType 日志类型 1订单付款 2订单退款
     */
    public void setMoneyLogType(Integer moneyLogType) {
        this.moneyLogType = moneyLogType;
    }

    /**
     * 获取操作金额
     *
     * @return use_money - 操作金额
     */
    public BigDecimal getUseMoney() {
        return useMoney;
    }

    /**
     * 设置操作金额
     *
     * @param useMoney 操作金额
     */
    public void setUseMoney(BigDecimal useMoney) {
        this.useMoney = useMoney;
    }

    /**
     * 获取日志时间
     *
     * @return create_time - 日志时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置日志时间
     *
     * @param createTime 日志时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", orderId=").append(orderId);
        sb.append(", moneyLogType=").append(moneyLogType);
        sb.append(", useMoney=").append(useMoney);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}