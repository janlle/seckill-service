package com.andy.seckill.domain;

import lombok.Data;

import java.util.Date;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-10-26
 **/
@Data
public class User {

    private Long userId;

    private String account;

    private String password;

    private String description;

    private Integer age;

    private Date createTime;

    private boolean deleted;

}
