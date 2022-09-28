package com.hps.shop;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.google.gson.annotations.JsonAdapter;
import com.hps.api.OrderService;
import com.hps.entity.Result;
import com.hps.shop.pojo.TradeOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * @author heps
 * @date 2019/12/16 9:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void testConfirmOrder() {
        TradeOrder order = new TradeOrder();

        order.setGoodsId(1L);
        order.setUserId(1L);
        order.setCouponId(1L);
        order.setAddress("湖北省");
        order.setGoodsNumber(1);
        order.setGoodsPrice(new BigDecimal(5000));
        order.setPayAmount(new BigDecimal(5000));
        order.setShippingFee(BigDecimal.ZERO);
        order.setOrderAmount(new BigDecimal(5000));
        order.setMoneyPaid(new BigDecimal(5000));

        final Result result = orderService.confirmOrder(order);
        System.out.println(result);
    }

    public static void main(String[] args) {
        TradeOrder order = new TradeOrder();

        order.setGoodsId(1L);
        order.setUserId(1L);
        order.setCouponId(1L);
        order.setAddress("湖北省");
        order.setGoodsNumber(1);
        order.setGoodsPrice(new BigDecimal(5000));
        order.setPayAmount(new BigDecimal(5000));
        order.setShippingFee(BigDecimal.ZERO);
        order.setOrderAmount(new BigDecimal(5000));
        order.setMoneyPaid(new BigDecimal(5000));

        /**
         * {
         *     "address": "湖北省",
         *     "couponId": 56765,
         *     "goodsId": 987,
         *     "goodsNumber": 1,
         *     "goodsPrice": 10.00,
         *     "moneyPaid": 1,
         *     "orderAmount": 10,
         *     "payAmount": 8,
         *     "shippingFee": 0,
         *     "userId": 5463
         * }
         */
        final String jsonString = JSONObject.toJSONString(order);

        System.out.println(jsonString);

    }
}
