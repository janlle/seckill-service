package com.leone.seckill.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * @author leone
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public OrderItemVO getOrderItemVO() {
        return orderItemVO;
    }

    public void setOrderItemVO(OrderItemVO orderItemVO) {
        this.orderItemVO = orderItemVO;
    }
}
