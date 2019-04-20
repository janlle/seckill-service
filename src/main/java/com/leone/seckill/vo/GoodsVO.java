package com.leone.seckill.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * @author leone
 * @since 2018-11-05
 **/
@Data
public class GoodsVO implements Serializable {

    private static final long serialVersionUID = 1147736477076287617L;

    private Long goodsId;

    private String goodsName;

    private String goodsTitle;

    private String goodsPicture;

    private String description;

    private Integer goodsPrice;

    private Integer discountPrice;

    private Integer goodsStock;

    private Integer goodsStatus;

    private Date startTime;

    private Date endTime;

    private Integer secKillStatus;

    private Integer remainSeconds;

}
