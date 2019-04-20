package com.leone.seckill.vo;

import lombok.Data;

/**
 * <p>
 *
 * @author leone
 * @since 2019-04-17
 **/
@Data
public class LoginVO {

    private String account;

    private String password;

    private String validateCode;

}
