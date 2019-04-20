package com.leone.seckill.exception;


/**
 * @author leone
 **/
public enum ExceptionMessage {

    SUCCESS(20000, "success"),
    WARNING(40000, "warning"),
    ERROR(50000, "error"),
    UNAUTHORIZED(40001, "未授权"),
    BAD_REQUEST(40002, "错误的请求"),
    AUTH_TOKEN(40010, "token 有误"),
    AUTH_TOKEN_EMPTY(40011, "token 为空"),
    AUTH_ACCOUNT_PASSWORD_WRONG(40012, "账号或密码错误"),
    SEC_KILL_SUCCESS(20000, "秒杀成功"),
    SEC_KILL_ERROR(40000, "秒杀失败"),
    SEC_KILL_QUEUE(10000, "秒杀排队中"),
    AUTH_PERMISSION(40014, "权限不足"),
    REQUEST_ARGUMENT(40015, "请求参数错误"),
    INTERNAL_SERVER_ERROR(40016, "服务器异常"),
    AUTH_CAPTCHA_WRONG(40017, "验证码错误"),
    AUTH_CAPTCHA_LOST(40018, "验证码已失效"),
    DELETE_IDS_FAIL(40020, "删除的id列表有误"),
    DELETE_FAIL(40021, "批量删除失败"),
    ORDER_NOT_EXIST(40022, "订单不存在"),
    USER_NOT_EXIST(40019, "用户不存在"),
    CAPTCHA_FAIL(40023, "验证码有误"),
    ACTIVITY_ORDER_STATUS_FAIL(40024, "活动订单状态不正确"),
    REFUNDS_CANNOT_BE_GREATER_THAN_ORDERS(40025, "退款商品不能大于订单商品数量"),
    ORDER_STATUS_FAIL(40027, "订单状态不正确"),
    AFTER_SALES_ORDER_ALREADY_EXISTS(40028, "售后订单已存在"),
    AVAILABLE_INTEGRAL_DEFICIENCY(40029, "可用积分不足"),
    USER_ID_AND_SHOPPING_CART_ID_DO_NOT_MATCH(40030, "用户id和购物车id不匹配"),
    THE_SHIPPING_ADDRESS_ALREADY_EXISTS(40031, "用户收货地址已存在"),
    WRONG_VALUE_OF_DISCOUNT_COUPONS(40032, "折扣券数值有误"),
    GOODS_EMPTY(40033, "商品已经秒杀完毕"),
    INVENTORY_SHORTAGE(40034, "商品库存不足"),
    NOT_GOODS_KILL_OR_GOODS_EMPTY(40035, "没有商品在秒杀或商品已经秒杀完毕"),
    PAYMENT_FAILURE(40036, "支付失败"),
    SIGNATURE_VERIFICATION_FAILED(40037, "签名校验失败"),
    WEI_XIN_PAY_FAIL(40038, "微信发起支付失败"),
    ACTIVITY_CLOSED(40039, "活动已结束"),
    THE_START_TIME_CANNOT_BE_GREATER_THAN_THE_END_TIME(40041, "开始时间不能大于结束时间"),
    THE_START_TIME_CANNOT_BE_LESS_THAN_THE_CURRENT_TIME(40042, "开始时间不能小于当前时间"),
    PERMISSION_DENIED(40043, "权限不足"),
    SEC_KILL_PATH_FAIL(40045, "秒杀路径不正确"),
    CURRENT_ORDER_IS_NOT_SHIPPED(40043, "当前订单未发货");


    private Integer code;

    private String message;

    ExceptionMessage() {
    }

    ExceptionMessage(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ExceptionMessage get(ExceptionMessage target) {
        for (ExceptionMessage result : ExceptionMessage.values()) {
            if (result == target) {
                return result;
            }
        }
        return null;
    }


}
