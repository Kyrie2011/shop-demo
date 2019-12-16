package com.hps.shop.service.impl;

import com.alibaba.fastjson.JSON;
import com.hps.api.PayService;
import com.hps.constant.ShopCode;
import com.hps.entity.Result;
import com.hps.exception.CastException;
import com.hps.shop.mapper.TradeMqProducerLogMapper;
import com.hps.shop.mapper.TradePayMapper;
import com.hps.shop.pojo.TradeMqProducerLog;
import com.hps.shop.pojo.TradePay;
import com.hps.utils.IDWorker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * @author heps
 * @date 2019/12/16 14:00
 */
@Slf4j
@Component
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private TradePayMapper payMapper;

    @Autowired
    private TradeMqProducerLogMapper mqProducerLogMapper;

    @Autowired
    private IDWorker idWorker;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Value("${rocketmq.producer.group}")
    private String groupName;

    @Value("${mq.pay.topic}")
    private String topic;

    @Value("${mq.pay.tag}")
    private String tag;

    @Override
    public Result createPayment(TradePay pay) {
        if (pay == null || pay.getOrderId() == null) {
            CastException.cast(ShopCode.SHOP_REQUEST_PARAMETER_VALID);
        }
        // 1.判断订单支付状态
        Example example = new Example(TradePay.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId", pay.getOrderId());
        criteria.andEqualTo("isPaid", pay.getIsPaid());
        int r = payMapper.selectCountByExample(example);
        if (r > 0) {
            CastException.cast(ShopCode.SHOP_PAYMENT_IS_PAID);
        }
        // 2.设置订单的状态为未支付
        pay.setIsPaid(ShopCode.SHOP_ORDER_PAY_STATUS_NO_PAY.getCode());
        // 3.保存支付订单
        pay.setPayId(idWorker.nextId());
        payMapper.insert(pay);

        return new Result(ShopCode.SHOP_SUCCESS.getSuccess(), ShopCode.SHOP_SUCCESS.getMessage());
    }

    @Override
    public Result callbackPayment(TradePay pay) {
        log.info("支付回调", pay);
        // 1.判断用户支付状态
        if (pay.getIsPaid() == ShopCode.SHOP_PAYMENT_IS_PAID.getCode()) {
            // 2.更新支付订单状态为已支付
            Long payId = pay.getPayId();
            TradePay tradePay = payMapper.selectByPrimaryKey(payId);
            // 判断支付订单是否存在
            if (tradePay == null) {
                CastException.cast(ShopCode.SHOP_PAYMENT_NOT_FOUND);
            }
            log.info("支付订单成功");
            tradePay.setIsPaid(ShopCode.SHOP_PAYMENT_IS_PAID.getCode());
            int r = payMapper.updateByPrimaryKey(tradePay);
            if (r == 1) {
                // 3.创建支付成功的消息
                TradeMqProducerLog mqProducerLog = new TradeMqProducerLog();
                mqProducerLog.setId(String.valueOf(idWorker.nextId()));
                mqProducerLog.setGroupName(groupName);
                mqProducerLog.setMsgTopic(topic);
                mqProducerLog.setMsgTag(tag);
                mqProducerLog.setMsgKey(String.valueOf(tradePay.getPayId()));
                mqProducerLog.setMsgBody(JSON.toJSONString(pay));
                mqProducerLog.setCreateTime(new Date());
                // 4.将消息持久化到数据库
                mqProducerLogMapper.insert(mqProducerLog);
                log.info("将支付成功消息保存到数据库");
                threadPoolTaskExecutor.execute(() -> {
                    // 5.发送消息到MQ
                    try {
                        SendResult sendResult = sendMessage(topic, tag, tradePay.getPayId().toString(), JSON.toJSONString(pay));
                        if (sendResult.getSendStatus() == SendStatus.SEND_OK) {
                            log.info("消息发送成功");
                            // 6.等待发送结果, 如果MQ接收到消息, 删除发送成功的消息
                            mqProducerLogMapper.deleteByPrimaryKey(mqProducerLog.getId());
                            log.info("持久化到数据库的消息删除成功");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        } else {
            CastException.cast(ShopCode.SHOP_PAYMENT_PAY_ERROR);
        }
        return new Result(ShopCode.SHOP_SUCCESS.getSuccess(), ShopCode.SHOP_SUCCESS.getMessage());
    }

    /**
     * 发送支付成功消息
     * @param topic
     * @param tag
     * @param keys
     * @param body
     */
    private SendResult sendMessage(String topic, String tag, String keys, String body) throws Exception {
        if (StringUtils.isEmpty(topic)) {
            CastException.cast(ShopCode.SHOP_MQ_TOPIC_IS_EMPTY);
        }
        if (StringUtils.isEmpty(body)) {
            CastException.cast(ShopCode.SHOP_MQ_MESSAGE_BODY_IS_EMPTY);
        }
        Message message = new Message(topic, tag, keys, body.getBytes());
        SendResult sendResult = rocketMQTemplate.getProducer().send(message);
        return sendResult;
    }
}
