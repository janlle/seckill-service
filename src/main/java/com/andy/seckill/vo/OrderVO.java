package com.andy.seckill.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-10-26
 **/
public class OrderVO implements Serializable {

    private static final long serialVersionUID = -4415046882353895041L;

    private Long orderId;

    private Long userId;

    private String username;

    private String address;

    private String phone;

    private Integer totalAmount;

    private Integer status;

    private Date createTime;

    private Date payTime;

    private OrderItemVO orderItemVO;



}
