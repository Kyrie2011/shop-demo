package com.hps.shop.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "trade_user")
public class TradeUser implements Serializable {
    /**
     * 用户ID
     */
    @Id
    @Column(name = "user_id")
    private Long userId;

    /**
     * 用户姓名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 用户密码
     */
    @Column(name = "user_password")
    private String userPassword;

    /**
     * 手机号
     */
    @Column(name = "user_mobile")
    private String userMobile;

    /**
     * 积分
     */
    @Column(name = "user_score")
    private Integer userScore;

    /**
     * 注册时间
     */
    @Column(name = "user_reg_time")
    private Date userRegTime;

    /**
     * 用户余额
     */
    @Column(name = "user_money")
    private BigDecimal userMoney;

    private static final long serialVersionUID = 1L;

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取用户姓名
     *
     * @return user_name - 用户姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户姓名
     *
     * @param userName 用户姓名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取用户密码
     *
     * @return user_password - 用户密码
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * 设置用户密码
     *
     * @param userPassword 用户密码
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    /**
     * 获取手机号
     *
     * @return user_mobile - 手机号
     */
    public String getUserMobile() {
        return userMobile;
    }

    /**
     * 设置手机号
     *
     * @param userMobile 手机号
     */
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile == null ? null : userMobile.trim();
    }

    /**
     * 获取积分
     *
     * @return user_score - 积分
     */
    public Integer getUserScore() {
        return userScore;
    }

    /**
     * 设置积分
     *
     * @param userScore 积分
     */
    public void setUserScore(Integer userScore) {
        this.userScore = userScore;
    }

    /**
     * 获取注册时间
     *
     * @return user_reg_time - 注册时间
     */
    public Date getUserRegTime() {
        return userRegTime;
    }

    /**
     * 设置注册时间
     *
     * @param userRegTime 注册时间
     */
    public void setUserRegTime(Date userRegTime) {
        this.userRegTime = userRegTime;
    }

    /**
     * 获取用户余额
     *
     * @return user_money - 用户余额
     */
    public BigDecimal getUserMoney() {
        return userMoney;
    }

    /**
     * 设置用户余额
     *
     * @param userMoney 用户余额
     */
    public void setUserMoney(BigDecimal userMoney) {
        this.userMoney = userMoney;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", userPassword=").append(userPassword);
        sb.append(", userMobile=").append(userMobile);
        sb.append(", userScore=").append(userScore);
        sb.append(", userRegTime=").append(userRegTime);
        sb.append(", userMoney=").append(userMoney);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}