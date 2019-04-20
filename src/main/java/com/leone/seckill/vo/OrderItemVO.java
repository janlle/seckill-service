package com.leone.seckill.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * @author leone
 * @since 2018-10-26
 **/
@Data
public class OrderItemVO implements Serializable {

    private static final long serialVersionUID = -8518689296551202836L;

    private Long orderItemId;

    private Long goodsId;

    private Long orderId;

    private Integer goodsPrice;

    private Integer goodsCount;

    private String goodsPicture;

    private String goodsDescription;

    private String goodsTitle;

    private Date createTime;

}
