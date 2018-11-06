package com.andy.seckill.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-11-05
 **/
@Data
public class OrderAddVO implements Serializable {

    private Long userId;

    private Long goodsId;

}
