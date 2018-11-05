package com.andy.seckill.vo;

import lombok.Data;

import java.util.Date;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-10-26
 **/
@Data
public class OrderDetailVO {

    private Long orderId;

    private Long userId;

    private Integer totalPrice;

    private Integer status;

    private Date createTime;

    private Date payTime;

    private String account;

    private Long goodsId;

}
