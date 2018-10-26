package com.andy.seckill.common;

/**
 * @author Leone
 **/
public enum ResultEnum {

    SUCCESS("SUCCESS", 20000),
    WARNING("WARNING", 40000),
    ERROR("ERROR", 50000);

    private String msg;

    private Integer code;

    ResultEnum() {
    }

    ResultEnum(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public ResultEnum get(ResultEnum target) {
        for (ResultEnum baseResult : ResultEnum.values()) {
            if (baseResult == target) {
                return baseResult;
            }
        }
        return null;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
