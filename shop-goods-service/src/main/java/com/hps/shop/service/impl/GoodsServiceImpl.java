package com.hps.shop.service.impl;

import com.hps.api.GoodsService;
import com.hps.constant.ShopCode;
import com.hps.entity.Result;
import com.hps.exception.CastException;
import com.hps.shop.mapper.TradeGoodsMapper;
import com.hps.shop.mapper.TradeGoodsNumberLogMapper;
import com.hps.shop.pojo.TradeGoods;
import com.hps.shop.pojo.TradeGoodsNumberLog;
import com.hps.utils.IDWorker;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author heps
 * @date 2019/12/15 20:55
 */
@Component
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private TradeGoodsMapper tradeGoodsMapper;

    @Autowired
    private TradeGoodsNumberLogMapper goodsNumberLogMapper;

    @Autowired
    private IDWorker idWorker;

    @Override
    public TradeGoods findOne(Long goodsId) {
        if (goodsId == null) {
            CastException.cast(ShopCode.SHOP_REQUEST_PARAMETER_VALID);
        }
        return tradeGoodsMapper.selectByPrimaryKey(goodsId);
    }

    @Override
    public Result reduceGoodsNum(TradeGoodsNumberLog tradeGoodsNumberLog) {
        if (tradeGoodsNumberLog == null ||
                tradeGoodsNumberLog.getGoodsNumber() == null ||
                tradeGoodsNumberLog.getOrderId() == null ||
                tradeGoodsNumberLog.getGoodsNumber() <= 0) {
            CastException.cast(ShopCode.SHOP_REQUEST_PARAMETER_VALID);
        }
        TradeGoods goods = tradeGoodsMapper.selectByPrimaryKey(tradeGoodsNumberLog.getGoodsId());
        if (goods.getGoodsNumber() < tradeGoodsNumberLog.getGoodsNumber()) {
            CastException.cast(ShopCode.SHOP_GOODS_NUM_NOT_ENOUGH);
        }
        goods.setGoodsNumber(goods.getGoodsNumber() - tradeGoodsNumberLog.getGoodsNumber());
        tradeGoodsMapper.updateByPrimaryKey(goods);

        tradeGoodsNumberLog.setId(idWorker.nextId());
        tradeGoodsNumberLog.setGoodsNumber(-(tradeGoodsNumberLog.getGoodsNumber()));
        tradeGoodsNumberLog.setLogTime(new Date());
        goodsNumberLogMapper.insert(tradeGoodsNumberLog);

        return new Result(ShopCode.SHOP_SUCCESS.getSuccess(), ShopCode.SHOP_SUCCESS.getMessage());
    }
}
