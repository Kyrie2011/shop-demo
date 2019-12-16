package com.hps.shop.mq;

import com.alibaba.fastjson.JSON;
import com.hps.constant.ShopCode;
import com.hps.shop.mapper.TradeOrderMapper;
import com.hps.shop.pojo.TradeOrder;
import com.hps.shop.pojo.TradePay;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author heps
 * @date 2019/12/16 15:22
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "${mq.pay.topic}", consumerGroup = "${mq.pay.consumer.group.name}", messageModel = MessageModel.BROADCASTING)
public class PaymentMQListener implements RocketMQListener<MessageExt> {

    @Autowired
    private TradeOrderMapper orderMapper;

    @Override
    public void onMessage(MessageExt messageExt) {
        try {
            // 1.解析消息内容
            String body = new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);
            TradePay tradePay = JSON.parseObject(body, TradePay.class);
            log.info("接收到消息: {}", body);
            if (tradePay.getOrderId() != null) {
                // 2.根据订单ID查询订单对象
                TradeOrder order = orderMapper.selectByPrimaryKey(tradePay.getOrderId());
                // 3.更改订单支付状态为已支付
                order.setPayStatus(ShopCode.SHOP_ORDER_PAY_STATUS_IS_PAY.getCode());
                // 4.更新订单数据到数据库
                orderMapper.updateByPrimaryKey(order);
                log.info("订单{}支付状态更改为已支付", order.getOrderId());
            }
        } catch (Exception e) {

        }
    }
}
