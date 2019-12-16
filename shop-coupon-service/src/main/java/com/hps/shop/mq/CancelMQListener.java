package com.hps.shop.mq;

import com.alibaba.fastjson.JSON;
import com.hps.constant.ShopCode;
import com.hps.entity.MqEntity;
import com.hps.shop.mapper.TradeCouponMapper;
import com.hps.shop.pojo.TradeCoupon;
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
    private TradeCouponMapper couponMapper;

    @Override
    public void onMessage(MessageExt messageExt) {
        try {
            // 1.解析消息内容
            String body = new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);
            MqEntity entity = JSON.parseObject(body, MqEntity.class);
            log.info("接收到消息, {}", entity);
            if (entity.getCouponId() != null) {
                // 2.查询优惠券信息
                TradeCoupon coupon = couponMapper.selectByPrimaryKey(entity.getCouponId());
                // 3.更改优惠券状态
                coupon.setUsedTime(null);
                coupon.setIsUsed(ShopCode.SHOP_COUPON_UNUSED.getCode());
                coupon.setOrderId(null);
                couponMapper.updateByPrimaryKey(coupon);
                log.info("回退优惠券成功: {}", entity.getCouponId());
            }
        } catch (Exception e) {
            log.error("回退优惠券失败, {}", e);
        }
    }
}
