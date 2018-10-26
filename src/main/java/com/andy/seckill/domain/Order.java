package com.andy.seckill.domain;

import lombok.Data;

import java.util.Date;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-10-26
 **/
@Data
public class Order {

	private Long orderId;

	private Long userId;

	private Integer totalPrice;

	private Integer status;

	private Date createTime;

	private Date payTime;

}
