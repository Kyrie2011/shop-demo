package com.hps.shop.service.impl;

import com.alibaba.fastjson.JSON;
import com.hps.api.CouponService;
import com.hps.api.GoodsService;
import com.hps.api.OrderService;
import com.hps.api.UserService;
import com.hps.constant.ShopCode;
import com.hps.entity.MqEntity;
import com.hps.entity.Result;
import com.hps.exception.CastException;
import com.hps.shop.mapper.TradeOrderMapper;
import com.hps.shop.pojo.*;
import com.hps.utils.IDWorker;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.lang.model.element.VariableElement;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author heps
 * @date 2019/12/15 20:31
 */
@Slf4j
@Component
@Service
public class OrderServiceImpl implements OrderService {

    @Reference
    private GoodsService goodsService;

    @Reference
    private UserService userService;

    @Reference
    private CouponService couponService;

    @Autowired
    private IDWorker idWorker;

    @Autowired
    private TradeOrderMapper tradeOrderMapper;

    @Value("${mq.order.topic}")
    private String topic;

    @Value("${mq.order.tag.cancel}")
    private String tag;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public Result confirmOrder(TradeOrder order) {
        String threadName = Thread.currentThread().getId() + Thread.currentThread().getName();
        System.out.println(threadName);
        // 1.校验订单
        checkOrder(order);
        // 2.生成预订单
        savePreOrder(order);
        try {
            // 3.扣减库存
            reduceGoodsNum(order);
            // 4.扣减优惠券
            updateCouponStatus(order);
            // 5.使用余额
            reduceMoneyPaid(order);
            // 6.确认订单
            updateOrderStatus(order);

            int a = 1 / 0;
            // 7.返回成功状态
            return new Result(ShopCode.SHOP_SUCCESS.getSuccess(), ShopCode.SHOP_SUCCESS.getMessage());
        } catch (Exception e) {
            String threadName2 = Thread.currentThread().getId() + Thread.currentThread().getName();
            System.out.println(threadName2+"------");
            // 1.确认订单失败, 发送消息
            MqEntity entity = new MqEntity();
            entity.setOrderId(order.getOrderId());
            entity.setUserId(order.getUserId());
            entity.setUserMoney(order.getMoneyPaid());
            entity.setGoodsId(order.getGoodsId());
            entity.setGoodsNum(order.getGoodsNumber());
            entity.setCouponId(order.getCouponId());
            // 2.订单确认失败消息
            try {
                sendCancelOrder(topic, tag, order.getOrderId().toString(), JSON.toJSONString(entity));
            } catch (Exception e1) {
                log.error("订单确认失败", e1);
            }
            return new Result(ShopCode.SHOP_FAIL.getSuccess(), ShopCode.SHOP_FAIL.getMessage());
        }
    }

    /**
     * 发送订单确认失败消息
     * @param topic
     * @param tag
     * @param keys
     * @param body
     */
    private void sendCancelOrder(String topic, String tag, String keys, String body) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        Message message = new Message(topic, tag, keys, body.getBytes());
        rocketMQTemplate.getProducer().send(message);
    }

    private void updateOrderStatus(TradeOrder order) {
        order.setOrderStatus(ShopCode.SHOP_ORDER_CONFIRM.getCode());
        order.setPayStatus(ShopCode.SHOP_ORDER_PAY_STATUS_NO_PAY.getCode());
        order.setConfirmTime(new Date());
        int r = tradeOrderMapper.updateByPrimaryKey(order);
        if (r <= 0) {
            CastException.cast(ShopCode.SHOP_ORDER_CONFIRM_FAIL);
        }
        log.info("订单:{}确认成功", order.getOrderId());
    }

    /**
     * 扣减余额
     * @param order
     */
    private void reduceMoneyPaid(TradeOrder order) {
        if (order.getMoneyPaid() != null && order.getMoneyPaid().compareTo(BigDecimal.ZERO) == 1) {
            TradeUserMoneyLog userMoneyLog = new TradeUserMoneyLog();
            userMoneyLog.setOrderId(order.getOrderId());
            userMoneyLog.setUserId(order.getUserId());
            userMoneyLog.setUseMoney(order.getMoneyPaid());
            userMoneyLog.setMoneyLogType(ShopCode.SHOP_USER_MONEY_PAID.getCode());
            userMoneyLog.setId(idWorker.nextId());

            Result result = userService.updateMoneyPaid(userMoneyLog);
            if (result.getSuccess().equals(ShopCode.SHOP_FAIL.getSuccess())) {
                CastException.cast(ShopCode.SHOP_USER_MONEY_REDUCE_FAIL);
            }
            log.info("订单:{}扣减余额成功", order.getOrderId());
        }
    }

    /**
     * 使用优惠券
     * @param order
     */
    private void updateCouponStatus(TradeOrder order) {
        if (order.getCouponId() != null) {
            TradeCoupon coupon = couponService.findOne(order.getCouponId());
            coupon.setOrderId(order.getOrderId());
            coupon.setIsUsed(ShopCode.SHOP_COUPON_ISUSED.getCode());
            coupon.setUsedTime(new Date());

            // 更新优惠券状态
            Result result = couponService.updateCouponStatus(coupon);
            if (result.getSuccess().equals(ShopCode.SHOP_FAIL.getSuccess())) {
                CastException.cast(ShopCode.SHOP_COUPON_USE_FAIL);
            }
            log.info("订单:{}, 使用优惠券", order.getOrderId());
        }
    }

    /**
     * 扣减库存
     * @param order
     */
    private void reduceGoodsNum(TradeOrder order) {
        TradeGoodsNumberLog tradeGoodsNumberLog = new TradeGoodsNumberLog();
        tradeGoodsNumberLog.setOrderId(order.getOrderId());
        tradeGoodsNumberLog.setGoodsId(order.getGoodsId());
        tradeGoodsNumberLog.setGoodsNumber(order.getGoodsNumber());
        Result result = goodsService.reduceGoodsNum(tradeGoodsNumberLog);
        if (result.getSuccess().equals(ShopCode.SHOP_FAIL.getSuccess())) {
            CastException.cast(ShopCode.SHOP_REDUCE_GOODS_NUM_FAIL);
        }
        log.info("订单:{}扣减库存成功", order.getOrderId().toString());
    }

    /**
     * 生成预订单
     * @param order
     * @return
     */
    private Long savePreOrder(TradeOrder order) {
        // 1.设置订单状态不可见
        order.setOrderStatus(ShopCode.SHOP_ORDER_NO_CONFIRM.getCode());
        // 2.设置订单ID
        long orderId = idWorker.nextId();
        order.setOrderId(orderId);
        // 3.核算订单运费
        BigDecimal shippingFee = calculateShippingFee(order.getOrderAmount());
        if (order.getShippingFee().compareTo(shippingFee) != 0) {
            CastException.cast(ShopCode.SHOP_ORDER_SHIPPINGFEE_INVALID);
        }
        // 4.核算订单总金额是否合法
        BigDecimal orderAmount = order.getGoodsPrice().multiply(new BigDecimal(order.getGoodsNumber()));
        orderAmount.add(shippingFee);
        if (order.getOrderAmount().compareTo(orderAmount) != 0) {
            CastException.cast(ShopCode.SHOP_ORDER_AMOUNT_INVALID);
        }
        // 5.判断用户是否使用余额
        BigDecimal moneyPaid = order.getMoneyPaid();
        if (moneyPaid != null) {
            // 5.1订单中余额是否合法
            int r = moneyPaid.compareTo(BigDecimal.ZERO);
            // 余额小于0
            if (r == -1) {
                CastException.cast(ShopCode.SHOP_MONEY_PAID_LESS_ZERO);
            }
            // 余额大于0
            if (r == 1) {
                TradeUser user = userService.findOne(order.getUserId());
                if (moneyPaid.compareTo(user.getUserMoney()) == 1) {
                    CastException.cast(ShopCode.SHOP_MONEY_PAID_INVALID);
                }
            }
        } else {
            order.setMoneyPaid(BigDecimal.ZERO);
        }
        // 6.判断用户是否使用优惠券
        Long couponId = order.getCouponId();
        if (couponId != null) {
            TradeCoupon coupon = couponService.findOne(couponId);
            if (coupon == null) {
                CastException.cast(ShopCode.SHOP_COUPON_NO_EXIST);
            }
            if (coupon.getIsUsed().intValue() == ShopCode.SHOP_COUPON_ISUSED.getCode()) {
                CastException.cast(ShopCode.SHOP_COUPON_ISUSED);
            }
            order.setCouponPaid(coupon.getCouponPrice());
        } else {
            order.setCouponPaid(BigDecimal.ZERO);
        }
        // 7.核算订单支付金额 订单总金额-余额-优惠券金额
        BigDecimal payAmount = order.getOrderAmount().subtract(order.getMoneyPaid()).subtract(order.getCouponPaid());
        order.setPayAmount(payAmount);
        // 8.设置下单时间
        order.setAddTime(new Date());
        // 9.保存订单到数据库
        tradeOrderMapper.insert(order);
        // 10.返回订单ID
        return orderId;
    }

    /**
     * 核算运费
     * @param orderAmount
     * @return
     */
    private BigDecimal calculateShippingFee(BigDecimal orderAmount) {
        if (orderAmount.compareTo(new BigDecimal(100)) == 1) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(0);
    }

    /**
     * 校验订单
     * @param order
     */
    private void checkOrder(TradeOrder order) {
        log.info("开始校验订单");
        // 1.校验订单是否存在
        if(order == null) {
            CastException.cast(ShopCode.SHOP_ORDER_INVALID);
        }
        // 2.校验订单中的商品是否存在
        TradeGoods goods = goodsService.findOne(order.getGoodsId());
        if (goods == null) {
            CastException.cast(ShopCode.SHOP_GOODS_NO_EXITST);
        }
        // 3.校验下单用户是否存在
        TradeUser user = userService.findOne(order.getUserId());
        if (user == null) {
            CastException.cast(ShopCode.SHOP_USER_NO_EXIST);
        }
        // 4.校验商品单价是否合法
        if (order.getGoodsPrice().compareTo(goods.getGoodsPrice()) != 0) {
            CastException.cast(ShopCode.SHOP_GOODS_PRICE_INVALID);
        }
        // 5.校验订单商品数量是否合法
        if (order.getGoodsNumber() > goods.getGoodsNumber()) {
            CastException.cast(ShopCode.SHOP_GOODS_NUM_NOT_ENOUGH);
        }
        log.info("校验订单通过");
    }
}
