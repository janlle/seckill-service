package com.andy.seckill.service;

import com.andy.seckill.domain.Goods;
import com.andy.seckill.domain.Order;
import com.andy.seckill.domain.OrderDetail;
import com.andy.seckill.mapper.OrderDetailMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-10-26
 **/
@Service
public class OrderDetailService {

    @Resource
    private OrderService orderService;

    @Resource
    private UserService userService;

    @Resource
    private OrderDetailMapper orderDetailMapper;

    /**
     * 保存订单详情
     *
     * @param goods
     * @param order
     */
    public OrderDetail save(Goods goods, Order order) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setCount(1);
        orderDetail.setCreateTime(new Date());
        orderDetail.setGoodsId(goods.getGoodsId());
        orderDetail.setOrderId(order.getOrderId());
        orderDetail.setPicture(goods.getPicture());
        orderDetail.setPrice(goods.getDiscountPrice());
        orderDetail.setTotal(goods.getDiscountPrice());
        orderDetailMapper.save(orderDetail);
        return orderDetail;
    }


}
