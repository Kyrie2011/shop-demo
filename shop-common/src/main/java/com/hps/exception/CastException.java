package com.hps.exception;

import com.hps.constant.ShopCode;
import lombok.extern.slf4j.Slf4j;

/**
 * @author heps
 * @date 2019/12/15 20:08
 */
@Slf4j
public class CastException {

    public static void cast(ShopCode shopCode) {
        log.error(shopCode.toString());
        throw new CustomerException(shopCode);
    }
}
