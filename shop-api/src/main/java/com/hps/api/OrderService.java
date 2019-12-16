package com.hps.api;

import com.hps.entity.Result;
import com.hps.shop.pojo.TradeOrder;

/**
 * @author hepengshuai
 * @date 2019/12/15 20:29
 */
public interface OrderService {

    /**
     * 下单接口
     * @param order
     * @return
     */
    Result confirmOrder(TradeOrder order);
}
