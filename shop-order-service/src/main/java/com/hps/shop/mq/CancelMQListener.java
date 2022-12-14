package com.hps.shop.mq;

import com.alibaba.fastjson.JSON;
import com.hps.constant.ShopCode;
import com.hps.entity.MqEntity;
import com.hps.shop.mapper.TradeOrderMapper;
import com.hps.shop.pojo.TradeOrder;
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
 * @date 2019/12/16 11:20
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "${mq.order.topic}", consumerGroup = "${mq.order.consumer.group.name}", messageModel = MessageModel.BROADCASTING)
public class CancelMQListener implements RocketMQListener<MessageExt> {

    @Autowired
    private TradeOrderMapper orderMapper;

    @Override
    public void onMessage(MessageExt messageExt) {
        try {
            // 1.解析消息内容
            String body = new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);
            MqEntity entity = JSON.parseObject(body, MqEntity.class);
            log.info("接收到消息, {}", entity);
            if (entity.getOrderId() != null) {
                // 2.查询订单
                TradeOrder order = orderMapper.selectByPrimaryKey(entity.getOrderId());
                // 3.更新订单状态为取消
                order.setOrderStatus(ShopCode.SHOP_ORDER_CANCEL.getCode());
                orderMapper.updateByPrimaryKey(order);
                log.info("订单{}订单状态设置为取消", entity.getOrderId());
            }
        } catch (Exception e) {
            log.error("订单取消失败", e);
        }
    }
}
