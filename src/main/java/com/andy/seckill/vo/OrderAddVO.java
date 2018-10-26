package com.andy.seckill.vo;

import lombok.Data;

import java.util.Set;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-10-26
 **/
@Data
public class OrderAddVO {

    private Set<Long> goodsId;

    private Integer count;

    private Long userId;


}
