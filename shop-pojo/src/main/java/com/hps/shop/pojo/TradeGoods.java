package com.hps.shop.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "trade_goods")
public class TradeGoods implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "goods_id")
    private Long goodsId;

    /**
     * 商品名称
     */
    @Column(name = "goods_name")
    private String goodsName;

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
     * 商品描述
     */
    @Column(name = "goods_desc")
    private String goodsDesc;

    /**
     * 添加时间
     */
    @Column(name = "add_time")
    private Date addTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     * @return goods_id - 主键
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 设置主键
     *
     * @param goodsId 主键
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取商品名称
     *
     * @return goods_name - 商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置商品名称
     *
     * @param goodsName 商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
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
     * 获取商品描述
     *
     * @return goods_desc - 商品描述
     */
    public String getGoodsDesc() {
        return goodsDesc;
    }

    /**
     * 设置商品描述
     *
     * @param goodsDesc 商品描述
     */
    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc == null ? null : goodsDesc.trim();
    }

    /**
     * 获取添加时间
     *
     * @return add_time - 添加时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 设置添加时间
     *
     * @param addTime 添加时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", goodsNumber=").append(goodsNumber);
        sb.append(", goodsPrice=").append(goodsPrice);
        sb.append(", goodsDesc=").append(goodsDesc);
        sb.append(", addTime=").append(addTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}