package com.hps.constant;

/**
 * @author heps
 * @date 2019/12/15 20:09
 */
public enum ShopCode {
    SHOP_SUCCESS(true, 1, "正确"),
    SHOP_FAIL(false, 0, "错误"),
    SHOP_REQUEST_PARAMETER_VALID(false, -1, "请求参数有误"),

    SHOP_ORDER_INVALID(false, 3, "订单无效"),
    SHOP_ORDER_SHIPPINGFEE_INVALID(false, 10002, "订单运费不正确"),
    SHOP_ORDER_PAY_STATUS_IS_PAY(true, 2, "订单已付款"),
    SHOP_ORDER_PAY_STATUS_NO_PAY(true, 0, "订单未付款"),
    SHOP_ORDER_CANCEL(false, 2, "订单已取消"),
    SHOP_ORDER_CONFIRM(true, 1, "订单已经确认"),
    SHOP_ORDER_CONFIRM_FAIL(false, 10005, "订单确认失败"),
    SHOP_ORDER_MESSAGE_STATUS_CANCEL(true, 1, "取消订单"),
    SHOP_ORDER_MSEEAGE_STATUS_ISPAID(true, 2, "取消订单"),
    SHOP_ORDER_NO_CONFIRM(false, 0, "订单未确认"),
    SHOP_ORDER_PAY_STATUS_PAYING(true, 1, "订单正在付款"),
    SHOP_ORDER_RETURNED(false, 4, "订单已退货"),
    SHOP_ORDER_SAVE_ERROR(false, 4, "订单保存失败"),
    SHOP_ORDER_STATUS_UPDATE_FAIL(false, 10001, "订单状态修改失败"),
    SHOP_ORDER_AMOUNT_INVALID(false, 10003, "订单总价格不正确"),

    SHOP_GOODS_NO_EXITST(false, 20001, "商品不存在"),
    SHOP_GOODS_NUM_NOT_ENOUGH(false, 20003, "商品库存不足"),
    SHOP_GOODS_PRICE_INVALID(false, 20002, "商品价格非法"),
    SHOP_REDUCE_GOODS_NUM_FAIL(false, 20004, "扣减库存失败"),
    SHOP_REDUCE_GOODS_NUM_EMPTY(false, 20005, "扣减库存失败"),

    SHOP_USER_MONEY_REFUND(true, 2, "退款"),
    SHOP_USER_NO_EXIST(false, 30002, "用户不存在"),
    SHOP_USER_IS_NULL(false, 30001, "用户账号不能为空"),
    SHOP_USER_MONEY_PAID(true, 1, "付款"),
    SHOP_USER_MONEY_REDUCE_FAIL(false, 30003, "余额扣减失败"),
    SHOP_USER_MONEY_REFUND_ALREADY(true, 30004, "订单已经退过款"),

    SHOP_COUPON_INVALID(false, 40002, "优惠券不合法"),
    SHOP_COUPON_ISUSED(true, 1, "优惠券已经使用"),
    SHOP_COUPON_NO_EXIST(false, 40001, "优惠券不存在"),
    SHOP_COUPON_UNUSED(false, 0, "优惠券未使用"),
    SHOP_COUPON_USE_FAIL(false, 40003, "优惠券使用失败"),

    SHOP_MONEY_PAID_LESS_ZERO(false, 50001, "余额不能小于0"),
    SHOP_MONEY_PAID_INVALID(false, 50002, "余额非法"),

    SHOP_MQ_MESSAGE_STATUS_SUCCESS(true, 1, "消息处理成功"),
    SHOP_MQ_MESSAGE_BODY_IS_EMPTY(false, 60002, "消息体不能为空"),
    SHOP_MQ_MESSAGE_STATUS_FAIL(false, 2, "消息处理失败"),
    SHOP_MQ_MESSAGE_STATUS_PROCESSING(true, 0, "消息正在处理"),
    SHOP_MQ_SEND_MESSAGE_FAIL(false, 60003, "消息发送失败"),
    SHOP_MQ_TOPIC_IS_EMPTY(false, 60001, "Topic不能为空"),

    SHOP_PAYMENT_IS_PAID(false, 70002, "支付订单已支付"),
    SHOP_PAYMENT_NOT_FOUND(false, 70001, "支付订单未找到"),
    SHOP_PAYMENT_PAY_ERROR(false, 70003, "订单支付失败"),
    ;

    private boolean success;

    private int code;

    private String message;

    ShopCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public boolean getSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
