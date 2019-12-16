package com.hps.api;

import com.hps.entity.Result;
import com.hps.shop.pojo.TradePay;

/**
 * @author hepengshuai
 * @date 2019/12/16 13:58
 */
public interface PayService {

    Result createPayment(TradePay pay);

    Result callbackPayment(TradePay pay);
}
