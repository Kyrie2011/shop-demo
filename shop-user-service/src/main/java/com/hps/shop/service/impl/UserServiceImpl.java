package com.hps.shop.service.impl;

import com.hps.api.UserService;
import com.hps.constant.ShopCode;
import com.hps.entity.Result;
import com.hps.exception.CastException;
import com.hps.shop.mapper.TradeUserMapper;
import com.hps.shop.mapper.TradeUserMoneyLogMapper;
import com.hps.shop.pojo.TradeUser;
import com.hps.shop.pojo.TradeUserMoneyLog;
import com.hps.utils.IDWorker;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author heps
 * @date 2019/12/15 21:06
 */
@Component
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TradeUserMapper tradeUserMapper;

    @Autowired
    private TradeUserMoneyLogMapper userMoneyLogMapper;

    @Autowired
    private IDWorker idWorker;

    @Override
    public TradeUser findOne(Long userId) {
        if (userId == null) {
            CastException.cast(ShopCode.SHOP_REQUEST_PARAMETER_VALID);
        }
        return tradeUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public Result updateMoneyPaid(TradeUserMoneyLog userMoneyLog) {
        // 1.校验参数是否合法
        if (userMoneyLog == null ||
                userMoneyLog.getUserId() == null ||
                userMoneyLog.getOrderId() == null ||
                userMoneyLog.getUseMoney() == null ||
                userMoneyLog.getUseMoney().compareTo(BigDecimal.ZERO) <= 0) {
            CastException.cast(ShopCode.SHOP_REQUEST_PARAMETER_VALID);
        }
        // 2.查询订单余额使用日志
        Example example = new Example(TradeUserMoneyLog.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId", userMoneyLog.getOrderId());
        criteria.andEqualTo("userId", userMoneyLog.getUserId());
        List<TradeUserMoneyLog> userMoneyLogs = userMoneyLogMapper.selectByExample(example);
        TradeUser user = tradeUserMapper.selectByPrimaryKey(userMoneyLog.getUserId());
        // 3.扣减余额
        if (userMoneyLog.getMoneyLogType() == ShopCode.SHOP_USER_MONEY_PAID.getCode()) {
            if (!CollectionUtils.isEmpty(userMoneyLogs)) {
                // 已经付款
                CastException.cast(ShopCode.SHOP_ORDER_PAY_STATUS_IS_PAY);
            }
            user.setUserMoney(user.getUserMoney().subtract(userMoneyLog.getUseMoney()));
        }
        // 4.回退余额
        if (userMoneyLog.getMoneyLogType() == ShopCode.SHOP_USER_MONEY_REFUND.getCode()) {
            if (CollectionUtils.isEmpty(userMoneyLogs)) {
                // 如果没有支付，则不能退款
                CastException.cast(ShopCode.SHOP_ORDER_PAY_STATUS_NO_PAY);
            }
            // 防止多次退款
            for (TradeUserMoneyLog moneyLog : userMoneyLogs) {
                if (moneyLog.getMoneyLogType() == ShopCode.SHOP_USER_MONEY_REFUND.getCode()) {
                    CastException.cast(ShopCode.SHOP_USER_MONEY_REFUND_ALREADY);
                }
            }
            user.setUserMoney(user.getUserMoney().add(userMoneyLog.getUseMoney()));
        }
        tradeUserMapper.updateByPrimaryKey(user);
        // 5.记录订单余额使用日志
        userMoneyLog.setId(idWorker.nextId());
        userMoneyLog.setCreateTime(new Date());
        userMoneyLogMapper.insert(userMoneyLog);
        return new Result(ShopCode.SHOP_SUCCESS.getSuccess(), ShopCode.SHOP_SUCCESS.getMessage());
    }
}
