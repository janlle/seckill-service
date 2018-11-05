package com.andy.seckill.vo;

import lombok.Data;

import java.util.Date;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-11-05
 **/
@Data
public class GoodsDetailVO {

    private Long goodsId;

    private String name;

    private String title;

    private String picture;

    private String description;

    private Integer price;

    private String discountPrice;

    private Integer stock;

    private Integer status;

    private Date startTime;

    private Date endTime;

}
