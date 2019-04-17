package com.andy.seckill.service;

import com.andy.seckill.domain.Goods;
import com.andy.seckill.domain.Order;
import com.andy.seckill.domain.OrderItem;
import com.andy.seckill.mapper.OrderItemMapper;
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
public class OrderItemService {

    @Resource
    private OrderService orderService;

    @Resource
    private UserService userService;

    @Resource
    private OrderItemMapper orderDetailMapper;

    /**
     * 保存订单详情
     *
     * @param goods
     * @param orderId
     */
    public OrderItem save(Goods goods, Long orderId) {
        OrderItem orderDetail = new OrderItem();
        orderDetail.setGoodsCount(1);
        orderDetail.setCreateTime(new Date());
        orderDetail.setGoodsId(goods.getGoodsId());
        orderDetail.setOrderId(orderId);
        orderDetail.setGoodsPicture(goods.getGoodsPicture());
        orderDetail.setGoodsPrice(goods.getDiscountPrice());
        orderDetailMapper.save(orderDetail);
        return orderDetail;
    }

}
