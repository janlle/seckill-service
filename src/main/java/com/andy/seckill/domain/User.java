package com.andy.seckill.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-10-26
 **/
@Entity
@Table(name = "t_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String account;

    private String password;

    private String description;

    private Integer age;

    private Date createTime;

    private boolean deleted;

}
