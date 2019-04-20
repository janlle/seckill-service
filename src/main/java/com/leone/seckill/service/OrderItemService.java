package com.leone.seckill.service;

import com.leone.seckill.domain.Goods;
import com.leone.seckill.domain.OrderItem;
import com.leone.seckill.mapper.OrderItemMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 *
 * @author leone
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
        OrderItem orderItem = new OrderItem();
        orderItem.setGoodsCount(1);
        orderItem.setCreateTime(new Date());
        orderItem.setGoodsId(goods.getGoodsId());
        orderItem.setOrderId(orderId);
        orderItem.setGoodsDescription(goods.getDescription());
        orderItem.setGoodsTitle(goods.getGoodsTitle());
        orderItem.setGoodsPicture(goods.getGoodsPicture());
        orderItem.setGoodsPrice(goods.getDiscountPrice());
        Long orderItemId = orderDetailMapper.save(orderItem);
        orderItem.setOrderItemId(orderItemId);
        return orderItem;
    }

}
