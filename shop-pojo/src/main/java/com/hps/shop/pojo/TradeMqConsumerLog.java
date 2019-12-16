package com.hps.shop.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "trade_mq_consumer_log")
public class TradeMqConsumerLog implements Serializable {
    /**
     * 消息ID
     */
    @Id
    @Column(name = "msg_id")
    private String msgId;

    /**
     * 消费者组名
     */
    @Column(name = "group_name")
    private String groupName;

    /**
     * Tag
     */
    @Column(name = "msg_tag")
    private String msgTag;

    /**
     * Key
     */
    @Column(name = "msg_key")
    private String msgKey;

    /**
     * 消息内容
     */
    @Column(name = "msg_body")
    private String msgBody;

    /**
     * 消费状态 0正在处理 1处理成功 2处理失败
     */
    @Column(name = "consumer_status")
    private Integer consumerStatus;

    /**
     * 消费次数
     */
    @Column(name = "consumer_times")
    private Integer consumerTimes;

    /**
     * 消费时间
     */
    @Column(name = "consumer_timestamp")
    private Date consumerTimestamp;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    /**
     * 获取消息ID
     *
     * @return msg_id - 消息ID
     */
    public String getMsgId() {
        return msgId;
    }

    /**
     * 设置消息ID
     *
     * @param msgId 消息ID
     */
    public void setMsgId(String msgId) {
        this.msgId = msgId == null ? null : msgId.trim();
    }

    /**
     * 获取消费者组名
     *
     * @return group_name - 消费者组名
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置消费者组名
     *
     * @param groupName 消费者组名
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    /**
     * 获取Tag
     *
     * @return msg_tag - Tag
     */
    public String getMsgTag() {
        return msgTag;
    }

    /**
     * 设置Tag
     *
     * @param msgTag Tag
     */
    public void setMsgTag(String msgTag) {
        this.msgTag = msgTag == null ? null : msgTag.trim();
    }

    /**
     * 获取Key
     *
     * @return msg_key - Key
     */
    public String getMsgKey() {
        return msgKey;
    }

    /**
     * 设置Key
     *
     * @param msgKey Key
     */
    public void setMsgKey(String msgKey) {
        this.msgKey = msgKey == null ? null : msgKey.trim();
    }

    /**
     * 获取消息内容
     *
     * @return msg_body - 消息内容
     */
    public String getMsgBody() {
        return msgBody;
    }

    /**
     * 设置消息内容
     *
     * @param msgBody 消息内容
     */
    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody == null ? null : msgBody.trim();
    }

    /**
     * 获取消费状态 0正在处理 1处理成功 2处理失败
     *
     * @return consumer_status - 消费状态 0正在处理 1处理成功 2处理失败
     */
    public Integer getConsumerStatus() {
        return consumerStatus;
    }

    /**
     * 设置消费状态 0正在处理 1处理成功 2处理失败
     *
     * @param consumerStatus 消费状态 0正在处理 1处理成功 2处理失败
     */
    public void setConsumerStatus(Integer consumerStatus) {
        this.consumerStatus = consumerStatus;
    }

    /**
     * 获取消费次数
     *
     * @return consumer_times - 消费次数
     */
    public Integer getConsumerTimes() {
        return consumerTimes;
    }

    /**
     * 设置消费次数
     *
     * @param consumerTimes 消费次数
     */
    public void setConsumerTimes(Integer consumerTimes) {
        this.consumerTimes = consumerTimes;
    }

    /**
     * 获取消费时间
     *
     * @return consumer_timestamp - 消费时间
     */
    public Date getConsumerTimestamp() {
        return consumerTimestamp;
    }

    /**
     * 设置消费时间
     *
     * @param consumerTimestamp 消费时间
     */
    public void setConsumerTimestamp(Date consumerTimestamp) {
        this.consumerTimestamp = consumerTimestamp;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", msgId=").append(msgId);
        sb.append(", groupName=").append(groupName);
        sb.append(", msgTag=").append(msgTag);
        sb.append(", msgKey=").append(msgKey);
        sb.append(", msgBody=").append(msgBody);
        sb.append(", consumerStatus=").append(consumerStatus);
        sb.append(", consumerTimes=").append(consumerTimes);
        sb.append(", consumerTimestamp=").append(consumerTimestamp);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}