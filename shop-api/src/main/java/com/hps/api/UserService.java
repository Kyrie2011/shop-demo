package com.hps.api;

import com.hps.entity.Result;
import com.hps.shop.pojo.TradeUser;
import com.hps.shop.pojo.TradeUserMoneyLog;

/**
 * @author hepengshuai
 * @date 2019/12/15 21:04
 */
public interface UserService {

    /**
     * 根据ID查询用户对象
     * @param userId
     * @return
     */
    TradeUser findOne(Long userId);

    /**
     * 更新余额
     * @param userMoneyLog
     * @return
     */
    Result updateMoneyPaid(TradeUserMoneyLog userMoneyLog);
}
