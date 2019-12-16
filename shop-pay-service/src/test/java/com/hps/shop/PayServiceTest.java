package com.hps.shop;

import com.hps.api.PayService;
import com.hps.constant.ShopCode;
import com.hps.shop.pojo.TradePay;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author heps
 * @date 2019/12/16 15:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PayServiceTest {

    @Autowired
    private PayService payService;

    @Test
    public void testCreatePayment() {
        TradePay pay = new TradePay();
        pay.setOrderId(1206477887507795968L);
        pay.setPayAmount(new BigDecimal(20));
        payService.createPayment(pay);
    }

    @Test
    public void testCallbackPayment() throws IOException {
        TradePay pay = new TradePay();
        pay.setPayId(1206479896898572288L);
        pay.setOrderId(1206477887507795968L);
        pay.setPayAmount(new BigDecimal(20));
        pay.setIsPaid(ShopCode.SHOP_PAYMENT_IS_PAID.getCode());

        payService.callbackPayment(pay);
        System.in.read();
    }
}
