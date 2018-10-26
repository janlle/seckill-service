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
@Table(name = "t_goods")
@Data
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goodsId;

    private String name;

    private String title;

    private String picture;

    private String description;

    private Integer price;

    private Integer stock;

    private Integer status;

}
