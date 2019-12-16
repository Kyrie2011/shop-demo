package com.hps.api;

import com.hps.entity.Result;
import com.hps.shop.pojo.TradeCoupon;

/**
 * 优惠券接口
 * @author hepengshuai
 * @date 2019/12/15 22:02
 */
public interface CouponService {

    /**
     * 根据ID查询优惠券对象
     * @param couponId
     * @return
     */
    TradeCoupon findOne(Long couponId);

    /**
     * 更新优惠券状态
     * @param coupon
     * @return
     */
    Result updateCouponStatus(TradeCoupon coupon);
}
