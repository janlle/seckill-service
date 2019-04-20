package com.leone.seckill.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * @author leone
 * @since 2018-10-26
 **/
@Data
public class GoodsListVO implements Serializable {

    private static final long serialVersionUID = -521834744651413626L;

    private Long goodsId;

    private String goodsName;

    private String goodsTitle;

    private String goodsPicture;

    private String description;

    private Integer goodsPrice;

    private String discountPrice;

    private Integer goodsStock;

    private Integer goodsStatus;


}
