package com.hps.shop.service.impl;

import com.hps.api.CouponService;
import com.hps.constant.ShopCode;
import com.hps.entity.Result;
import com.hps.exception.CastException;
import com.hps.shop.mapper.TradeCouponMapper;
import com.hps.shop.pojo.TradeCoupon;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author heps
 * @date 2019/12/15 22:42
 */
@Component
@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private TradeCouponMapper couponMapper;

    @Override
    public TradeCoupon findOne(Long couponId) {
        if (couponId == null) {
            CastException.cast(ShopCode.SHOP_REQUEST_PARAMETER_VALID);
        }
        return couponMapper.selectByPrimaryKey(couponId);
    }

    @Override
    public Result updateCouponStatus(TradeCoupon coupon) {
        if (coupon == null || coupon.getCouponId() == null) {
            CastException.cast(ShopCode.SHOP_REQUEST_PARAMETER_VALID);
        }
        couponMapper.updateByPrimaryKey(coupon);
        return new Result(ShopCode.SHOP_SUCCESS.getSuccess(), ShopCode.SHOP_SUCCESS.getMessage());
    }
}
