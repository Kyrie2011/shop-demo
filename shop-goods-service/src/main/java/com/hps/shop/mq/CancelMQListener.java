package com.hps.shop.mq;

import com.alibaba.fastjson.JSON;
import com.hps.constant.ShopCode;
import com.hps.entity.MqEntity;
import com.hps.shop.mapper.TradeGoodsMapper;
import com.hps.shop.mapper.TradeGoodsNumberLogMapper;
import com.hps.shop.mapper.TradeMqConsumerLogMapper;
import com.hps.shop.pojo.TradeGoods;
import com.hps.shop.pojo.TradeGoodsNumberLog;
import com.hps.shop.pojo.TradeMqConsumerLog;
import com.hps.utils.IDWorker;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * @author heps
 * @date 2019/12/16 11:20
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "${mq.order.topic}", consumerGroup = "${mq.order.consumer.group.name}", messageModel = MessageModel.BROADCASTING)
public class CancelMQListener implements RocketMQListener<MessageExt> {

    @Value("${mq.order.consumer.group.name}")
    private String groupName;

    @Autowired
    private TradeMqConsumerLogMapper mqConsumerLogMapper;

    @Autowired
    private TradeGoodsMapper goodsMapper;

    @Autowired
    private TradeGoodsNumberLogMapper goodsNumberLogMapper;

    @Autowired
    private IDWorker idWorker;

    @Override
    public void onMessage(MessageExt messageExt) {
        String msgId = null;
        String tags = null;
        String keys = null;
        String body = null;
        TradeMqConsumerLog mqConsumerLog = null;
        try {
            // 1.解析消息内容
            msgId = messageExt.getMsgId();
            tags = messageExt.getTags();
            keys = messageExt.getKeys();
            body = new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);
            // 2.查询消息消费记录
            Example example = new Example(TradeMqConsumerLog.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("msgTag", tags);
            criteria.andEqualTo("msgKey", keys);
            criteria.andEqualTo("groupName", groupName);
            mqConsumerLog = mqConsumerLogMapper.selectOneByExample(example);

            if (mqConsumerLog != null) {
                // 3.判断如果消费过
                Integer status = mqConsumerLog.getConsumerStatus();
                // 处理过
                if (ShopCode.SHOP_MQ_MESSAGE_STATUS_SUCCESS.getCode() == status) {
                    log.info("消息:{}已经处理过", msgId);
                    return;
                }
                // 正在处理
                if (ShopCode.SHOP_MQ_MESSAGE_STATUS_PROCESSING.getCode() == status) {
                    log.info("消息:{}正在处理", msgId);
                    return;
                }
                // 处理失败
                if (ShopCode.SHOP_MQ_MESSAGE_STATUS_FAIL.getCode() == status) {
                    Integer times = mqConsumerLog.getConsumerTimes();
                    if (times > 3) {
                        log.info("消息:{}处理超过3次，不能再进行处理", msgId);
                        return;
                    }
                    mqConsumerLog.setConsumerStatus(ShopCode.SHOP_MQ_MESSAGE_STATUS_PROCESSING.getCode());
                    Example example1 = new Example(TradeMqConsumerLog.class);
                    Example.Criteria criteria1 = example1.createCriteria();
                    criteria1.andEqualTo("msgTag", tags);
                    criteria1.andEqualTo("msgKey", keys);
                    criteria1.andEqualTo("groupName", groupName);
                    criteria1.andEqualTo("consumerTimes", mqConsumerLog.getConsumerTimes());
                    int r = mqConsumerLogMapper.updateByExampleSelective(mqConsumerLog, example1);
                    if (r <= 0) {
                        // 未修改成功，其他线程并发修改
                        log.info("并发修改, 稍后处理");
                        return;
                    }
                }
            } else {
                // 4.判断如果没有消费过
                mqConsumerLog = new TradeMqConsumerLog();
                mqConsumerLog.setMsgTag(tags);
                mqConsumerLog.setMsgKey(keys);
                mqConsumerLog.setConsumerStatus(ShopCode.SHOP_MQ_MESSAGE_STATUS_PROCESSING.getCode());
                mqConsumerLog.setMsgBody(body);
                mqConsumerLog.setMsgId(msgId);
                mqConsumerLog.setConsumerTimes(0);

                mqConsumerLogMapper.insertSelective(mqConsumerLog);
            }
            // 5.回退库存
            MqEntity entity = JSON.parseObject(body, MqEntity.class);
            Long goodsId = entity.getGoodsId();
            TradeGoods goods = goodsMapper.selectByPrimaryKey(goodsId);
            goods.setGoodsNumber(goods.getGoodsNumber() + entity.getGoodsNum());
            goodsMapper.updateByPrimaryKey(goods);

            // 6.记录消息消费日志
            TradeGoodsNumberLog goodsNumberLog = new TradeGoodsNumberLog();
            goodsNumberLog.setId(idWorker.nextId());
            goodsNumberLog.setOrderId(entity.getOrderId());
            goodsNumberLog.setGoodsId(goodsId);
            goodsNumberLog.setGoodsNumber(entity.getGoodsNum());
            goodsNumberLog.setLogTime(new Date());
            goodsNumberLogMapper.insert(goodsNumberLog);

            // 7.将消息处理状态改为成功
            mqConsumerLog.setConsumerStatus(ShopCode.SHOP_MQ_MESSAGE_STATUS_SUCCESS.getCode());
            mqConsumerLog.setConsumerTimestamp(new Date());
            mqConsumerLogMapper.updateByPrimaryKey(mqConsumerLog);

            log.info("回退库存成功");
        } catch (Exception e) {
            log.error("消息处理失败", e);
            Example example = new Example(TradeMqConsumerLog.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("msgTag", tags);
            criteria.andEqualTo("msgKey", keys);
            criteria.andEqualTo("groupName", groupName);
            mqConsumerLog = mqConsumerLogMapper.selectOneByExample(example);
            if (mqConsumerLog == null) {
                mqConsumerLog = new TradeMqConsumerLog();
                mqConsumerLog.setMsgId(msgId);
                mqConsumerLog.setMsgTag(tags);
                mqConsumerLog.setMsgKey(keys);
                mqConsumerLog.setConsumerStatus(ShopCode.SHOP_MQ_MESSAGE_STATUS_FAIL.getCode());
                mqConsumerLog.setMsgBody(body);
                mqConsumerLog.setConsumerTimes(1);
                mqConsumerLogMapper.insert(mqConsumerLog);
            } else {
                mqConsumerLog.setConsumerTimes(mqConsumerLog.getConsumerTimes() + 1);
                mqConsumerLogMapper.updateByPrimaryKey(mqConsumerLog);
            }
        }
    }
}
