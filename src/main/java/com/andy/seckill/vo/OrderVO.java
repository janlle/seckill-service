package com.andy.seckill.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-10-26
 **/
@Data
public class OrderVO implements Serializable {

    private Long orderId;

    private Long userId;

    private Integer totalPrice;

    private Integer status;

    private Date createTime;

    private Date payTime;

    private List<OrderDetailVO> orderDetailVO;

}
