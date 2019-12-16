package com.hps.exception;

import com.hps.constant.ShopCode;

/**
 * @author heps
 * @date 2019/12/15 20:08
 */
public class CustomerException extends RuntimeException{

    private ShopCode shopCode;

    public CustomerException(ShopCode shopCode) {
        this.shopCode = shopCode;
    }
}
