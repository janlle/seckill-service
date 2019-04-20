package com.leone.seckill.exception;

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

    public SecKillException(ExceptionMessage exceptionMessage) {
        this.code = exceptionMessage.getCode();
        this.message = exceptionMessage.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
