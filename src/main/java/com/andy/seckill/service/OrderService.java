package com.andy.seckill.service;

import com.andy.seckill.domain.Order;
import com.andy.seckill.domain.User;
import com.andy.seckill.mapper.OrderMapper;
import com.andy.seckill.vo.GoodsDetailVO;
import com.andy.seckill.vo.OrderAddVO;
import com.andy.seckill.vo.OrderDetailVO;
import com.andy.seckill.vo.OrderVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private UserService userService;

    @Resource
    private GoodsService goodsService;

    /**
     * 创建订单
     *
     * @param orderAddVO
     * @return
     */
    public OrderDetailVO createOrder(OrderAddVO orderAddVO) {
        Order order = new Order();
        Date date = new Date();
        order.setCreateTime(date);
        order.setStatus(0);
        order.setTotal(0);
        order.setUserId(orderAddVO.getUserId());

        GoodsDetailVO goodsDetailVO = goodsService.findOne(orderAddVO.getGoodsId());

        // 保存订单
        int result = orderMapper.save(order);

        OrderDetailVO orderDetailVO = new OrderDetailVO();
        orderDetailVO.setCount(1);
        BeanUtils.copyProperties(order, orderDetailVO);


        return orderDetailVO;
    }


    /**
     * @param orderId
     * @return
     */
    public OrderDetailVO findOne(Long orderId) {
        OrderVO orderVO = orderMapper.findOne(orderId);
        OrderDetailVO orderDetailVO = new OrderDetailVO();
        BeanUtils.copyProperties(orderVO, orderDetailVO);
        User user = userService.findOne(orderVO.getUserId());

        return orderDetailVO;
    }

    /**
     * @param userId
     * @param goodsId
     * @return
     */
    public OrderVO findByUserIdAndGoodsId(Long userId, Long goodsId) {
        return orderMapper.findByUserIdAndOrderId(userId, goodsId);
    }
}
