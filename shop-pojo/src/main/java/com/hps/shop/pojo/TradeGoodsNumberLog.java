package com.hps.shop.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "trade_goods_number_log")
public class TradeGoodsNumberLog implements Serializable {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 商品ID
     */
    @Column(name = "goods_id")
    private Long goodsId;

    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 库存数量
     */
    @Column(name = "goods_number")
    private Integer goodsNumber;

    /**
     * 记录时间
     */
    @Column(name = "log_time")
    private Date logTime;

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
     * 获取库存数量
     *
     * @return goods_number - 库存数量
     */
    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    /**
     * 设置库存数量
     *
     * @param goodsNumber 库存数量
     */
    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    /**
     * 获取记录时间
     *
     * @return log_time - 记录时间
     */
    public Date getLogTime() {
        return logTime;
    }

    /**
     * 设置记录时间
     *
     * @param logTime 记录时间
     */
    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", orderId=").append(orderId);
        sb.append(", goodsNumber=").append(goodsNumber);
        sb.append(", logTime=").append(logTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}