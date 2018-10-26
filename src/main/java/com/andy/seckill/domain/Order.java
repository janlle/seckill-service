package com.andy.seckill.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-10-26
 **/
@Entity
@Table(name = "t_order")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

	private Long userId;

	private Integer totalPrice;

	private Integer status;

	private Date createTime;

	private Date payTime;

}
