package com.hps.api;

import com.hps.entity.Result;
import com.hps.shop.pojo.TradeGoods;
import com.hps.shop.pojo.TradeGoodsNumberLog;

/**
 * @author heps
 * @date 2019/12/15 20:52
 */
public interface GoodsService {

    /**
     * 根据ID查询商品对象
     * @param goodsId
     * @return
     */
    TradeGoods findOne(Long goodsId);

    /**
     * 扣减库存
     * @param tradeGoodsNumberLog
     * @return
     */
    Result reduceGoodsNum(TradeGoodsNumberLog tradeGoodsNumberLog);
}
