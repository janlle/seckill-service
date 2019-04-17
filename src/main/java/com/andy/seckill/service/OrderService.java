package com.andy.seckill.service;

import com.andy.seckill.common.RedisPrefix;
import com.andy.seckill.domain.Goods;
import com.andy.seckill.domain.Order;
import com.andy.seckill.domain.OrderItem;
import com.andy.seckill.exception.ExceptionMessage;
import com.andy.seckill.mapper.OrderMapper;
import com.andy.seckill.vo.OrderAddVO;
import com.andy.seckill.vo.OrderItemVO;
import com.andy.seckill.vo.OrderVO;
import org.apache.shiro.util.Assert;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private OrderItemService orderDetailService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 创建订单
     *
     * @param orderAddVO
     * @return
     */
    @Transactional
    public OrderVO createOrder(OrderAddVO orderAddVO) {
        Goods goods = goodsService.findByGoodsId(orderAddVO.getGoodsId());

        Order order = new Order();
        order.setCreateTime(new Date());
        order.setStatus(0);
        order.setTotalAmount(goods.getGoodsPrice());
        order.setUserId(orderAddVO.getUserId());
        // 保存订单
        Long orderId = orderMapper.save(order);

        // 保存订单项
        OrderItem orderItem = orderDetailService.save(goods, orderId);
        OrderItemVO orderItemVO = new OrderItemVO();

        BeanUtils.copyProperties(orderItem, orderItemVO);

        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(order, orderItem);
        orderVO.setOrderItemVO(orderItemVO);

        // 保存用户秒杀到的订单到redis中
        redisTemplate.opsForValue().set(RedisPrefix.ORDER_PREFIX + order.getUserId() + "-" + order.getOrderId(), orderVO);
        return orderVO;
    }


    /**
     * 根据订单id查找订单
     *
     * @param orderId
     * @return
     */
    public OrderVO findOne(Long orderId) {
        OrderVO orderVO = orderMapper.findOne(orderId);
        Assert.notNull(orderVO, ExceptionMessage.ORDER_NOT_EXIST.getMessage());
        return orderVO;
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
