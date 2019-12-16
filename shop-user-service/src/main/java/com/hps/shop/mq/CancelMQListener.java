package com.hps.shop.mq;

import com.alibaba.fastjson.JSON;
import com.hps.api.UserService;
import com.hps.constant.ShopCode;
import com.hps.entity.MqEntity;
import com.hps.shop.pojo.TradeUserMoneyLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author heps
 * @date 2019/12/16 11:20
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "${mq.order.topic}", consumerGroup = "${mq.order.consumer.group.name}", messageModel = MessageModel.BROADCASTING)
public class CancelMQListener implements RocketMQListener<MessageExt> {

    @Autowired
    private UserService userService;

    @Override
    public void onMessage(MessageExt messageExt) {
        try {
            // 1.解析消息内容
            String body = new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);
            MqEntity entity = JSON.parseObject(body, MqEntity.class);
            log.info("接收到消息, {}", entity);
            if (entity.getUserMoney() != null && entity.getUserMoney().compareTo(BigDecimal.ZERO) > 0) {
                // 2.调用业务层, 进行余额修改
                TradeUserMoneyLog userMoneyLog = new TradeUserMoneyLog();
                userMoneyLog.setUseMoney(entity.getUserMoney());
                userMoneyLog.setMoneyLogType(ShopCode.SHOP_USER_MONEY_REFUND.getCode());
                userMoneyLog.setUserId(entity.getUserId());
                userMoneyLog.setOrderId(entity.getOrderId());
                userService.updateMoneyPaid(userMoneyLog);
                log.info("余额回退成功");
            }
        } catch (Exception e) {
            log.error("余额回退失败", e);
        }
    }
}
