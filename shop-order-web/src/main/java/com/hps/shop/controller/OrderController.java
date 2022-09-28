package com.hps.shop.controller;

import com.hps.api.OrderService;
import com.hps.entity.Result;
import com.hps.shop.pojo.TradeOrder;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heps
 * @date 2019/12/16 16:06
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Reference(retries = 0)
    private OrderService orderService;

    @PostMapping("/confirm")
    public Result confirmOrder(@RequestBody TradeOrder order) {
        return orderService.confirmOrder(order);
    }
}
