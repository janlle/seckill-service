package com.andy.seckill.vo;

import lombok.Data;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-10-26
 **/
@Data
public class GoodsVO {

    private Long goodsId;

    private String name;

    private String title;

    private String picture;

    private String description;

    private Integer price;

    private Integer stock;

    private Integer status;

}
