package com.leone.seckill.exception;

import java.io.Serializable;

/**
 * @author leone
 * @since 2018-08-09
 **/
public class ExceptionResult implements Serializable {

    private static final long serialVersionUID = -5111800217440234792L;

    private Integer code;

    private String message;

    public ExceptionResult(Integer code) {
        this.code = code;
    }

    public ExceptionResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
