package com.hps.shop.controller;

import com.hps.api.PayService;
import com.hps.entity.Result;
import com.hps.shop.pojo.TradePay;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heps
 * @date 2019/12/16 16:16
 */
@RestController
@RequestMapping("/pay")
public class PayController {

    @Reference
    private PayService payService;

    @PostMapping("/createPayment")
    public Result createPayment(@RequestBody TradePay pay) {
        return payService.createPayment(pay);
    }

    @PostMapping("/callbackPayment")
    public Result callbackPayment(@RequestBody TradePay pay) {
        return payService.callbackPayment(pay);
    }

}
