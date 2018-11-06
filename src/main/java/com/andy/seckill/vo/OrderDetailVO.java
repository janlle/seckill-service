package com.andy.seckill.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-10-26
 **/
@Data
public class OrderDetailVO implements Serializable {

    private Long orderDetailId;

    private Long goodsId;

    private Long orderId;

    private String picture;

    private Integer price;

    private Integer count;

    private Integer total;

    private Date createTime;

    private Date updateTime;

}
