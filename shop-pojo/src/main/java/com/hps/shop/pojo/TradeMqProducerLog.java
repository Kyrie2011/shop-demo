package com.hps.shop.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "trade_mq_producer_log")
public class TradeMqProducerLog implements Serializable {
    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 生产者组名
     */
    @Column(name = "group_name")
    private String groupName;

    /**
     * 消息主题
     */
    @Column(name = "msg_topic")
    private String msgTopic;

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
     * 消息状态 0未处理 1已处理
     */
    @Column(name = "msg_status")
    private Integer msgStatus;

    /**
     * 记录时间
     */
    @Column(name = "create_time")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取生产者组名
     *
     * @return group_name - 生产者组名
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置生产者组名
     *
     * @param groupName 生产者组名
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    /**
     * 获取消息主题
     *
     * @return msg_topic - 消息主题
     */
    public String getMsgTopic() {
        return msgTopic;
    }

    /**
     * 设置消息主题
     *
     * @param msgTopic 消息主题
     */
    public void setMsgTopic(String msgTopic) {
        this.msgTopic = msgTopic == null ? null : msgTopic.trim();
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
     * 获取消息状态 0未处理 1已处理
     *
     * @return msg_status - 消息状态 0未处理 1已处理
     */
    public Integer getMsgStatus() {
        return msgStatus;
    }

    /**
     * 设置消息状态 0未处理 1已处理
     *
     * @param msgStatus 消息状态 0未处理 1已处理
     */
    public void setMsgStatus(Integer msgStatus) {
        this.msgStatus = msgStatus;
    }

    /**
     * 获取记录时间
     *
     * @return create_time - 记录时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置记录时间
     *
     * @param createTime 记录时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", groupName=").append(groupName);
        sb.append(", msgTopic=").append(msgTopic);
        sb.append(", msgTag=").append(msgTag);
        sb.append(", msgKey=").append(msgKey);
        sb.append(", msgBody=").append(msgBody);
        sb.append(", msgStatus=").append(msgStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}