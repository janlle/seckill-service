package com.andy.seckill.service;

import com.andy.seckill.domain.Order;
import com.andy.seckill.mapper.OrderMapper;
import com.andy.seckill.vo.OrderAddVO;
import com.andy.seckill.vo.OrderDetailVO;
import com.andy.seckill.vo.OrderVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class OrderService {

    @Resource
    private OrderMapper orderMapper;


    /**
     * 创建订单
     *
     * @param orderAddVO
     * @return
     */
    public OrderVO createOrder(OrderAddVO orderAddVO) {
        Order order = new Order();
        Date date = new Date();
        order.setCreateTime(date);
        order.setStatus(0);
        order.setTotalPrice(0);
        order.setUserId(orderAddVO.getUserId());

        OrderVO vo = new OrderVO();


        return null;
    }


    /**
     * @param orderId
     * @return
     */
    public OrderDetailVO findOne(Long orderId) {


        return null;
    }

    /**
     * @param userId
     * @param goodsId
     * @return
     */
    public Order findByUserIdAndGoodsId(Long userId, Long goodsId) {


        return null;
    }
}
