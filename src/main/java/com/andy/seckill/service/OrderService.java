package com.andy.seckill.service;

import com.andy.seckill.common.RedisPrefix;
import com.andy.seckill.domain.Goods;
import com.andy.seckill.domain.Order;
import com.andy.seckill.domain.OrderItem;
import com.andy.seckill.domain.User;
import com.andy.seckill.mapper.OrderMapper;
import com.andy.seckill.vo.OrderAddVO;
import com.andy.seckill.vo.OrderDetailVO;
import com.andy.seckill.vo.OrderVO;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Resource
    private OrderDetailService orderDetailService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

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

        Goods goods = goodsService.findByGoodsId(orderAddVO.getGoodsId());

        // 保存订单
        int result = orderMapper.save(order);

        // 保存详情
        OrderItem orderDetail = orderDetailService.save(goods, order);

        OrderDetailVO orderDetailVO = new OrderDetailVO();
        BeanUtils.copyProperties(orderDetail, orderDetailVO);

        // 保存用户秒杀到的订单到redis中
        redisTemplate.opsForValue().set(RedisPrefix.ORDER_PREFIX + orderAddVO.getUserId() + "-" + goods.getGoodsId(), order);

        return orderDetailVO;
    }


    /**
     * 根据订单id查找订单
     *
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
     * 根据订单id查找订单
     *
     * @param userId
     * @param goodsId
     * @return
     */
    public OrderVO findByUserIdAndGoodsId(Long userId, Long goodsId) {
        return orderMapper.findByUserIdAndOrderId(userId, goodsId);
    }
}
