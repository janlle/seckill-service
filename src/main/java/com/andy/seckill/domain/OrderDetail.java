package com.andy.seckill.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-10-26
 **/
@Entity
@Table(name = "t_order_detail")
@Data
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailId;

    private Long goodsId;

    private String picture;

    private Integer price;

    private Integer count;

}
