package com.andy.seckill.exception;

/**
 * @author leone
 **/
public enum ExceptionMessage {

    PERMISSION_DENIED(40001, "权限不足"),
    AUTH_TOKEN(40010, "auth.token.wrong"),
    AUTH_TOKEN_EMPTY(40011, "auth.token.empty"),
    SERVER_ERROR(40012, "server.error"),
    PRODUCT_NOT_EXISTS(40013, "商品不存在"),
    ORDER_NOT_EXISTS(40014, "订单不存在"),
    USER_NOT_EXISTS(40015, "用户不存在"),
    USER_PHONE_OR_NUMBER_EXISTS(40016, "用户手机号或会员号已存在"),
    GUEST_NOT_EXISTS(40017, "访客不存在"),
    CURRENT_GUEST_IS_USER(40018, "当前访客已经是会员"),
    WAITER_NOT_EXISTS(40019, "服务员不存在"),
    INVALIDATE_PHONE_PASSWORD(40020, "无效的账号或密码"),
    STORE_NOT_EXISTS(40021, "商家不存在"),
    VIP_USER_NOT_EXISTS(40022, "会员不存在"),
    BINDING_FAILED(40023, "会员绑定失败"),
    ;


    private Integer code;

    private String message;

    ExceptionMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    ExceptionMessage() {
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
}
