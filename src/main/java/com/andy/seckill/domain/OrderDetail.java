package com.andy.seckill.domain;

import lombok.Data;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-10-26
 **/
@Data
public class OrderDetail {

    private Long orderId;

    private Long goodsId;

    private String picture;

    private Integer price;

    private Integer count;

}
