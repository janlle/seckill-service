package com.andy.seckill.exception;

import com.andy.seckill.common.MessageEnum;

/**
 * <p>
 *
 * @author leone
 * @since 2018-04-17
 **/
public class SecKillException extends RuntimeException {

    private static final long serialVersionUID = -3428229214466047373L;

    private Integer code;

    private String message;

    public SecKillException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public SecKillException() {
    }

    public SecKillException(MessageEnum messageEnum) {
        this.code = messageEnum.getCode();
        this.message = messageEnum.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
