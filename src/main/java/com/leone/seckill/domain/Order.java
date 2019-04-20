package com.leone.seckill.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * <p> 由于秒杀业务的订单和普通电商的订单表设计不同所以订单详情就只有一件商品
 *
 * @author leone
 * @since 2018-10-26
 **/
public class Order implements Serializable {

    private static final long serialVersionUID = 8007133563118806498L;

    private Long orderId;

    private Long userId;

    private Integer totalAmount;

    private Integer status;

    private Date createTime;

    private Date payTime;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
}
