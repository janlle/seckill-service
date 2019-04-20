package com.leone.seckill.service;

import com.leone.seckill.common.RedisPrefix;
import com.leone.seckill.domain.Goods;
import com.leone.seckill.domain.Order;
import com.leone.seckill.domain.OrderItem;
import com.leone.seckill.domain.User;
import com.leone.seckill.exception.ExceptionMessage;
import com.leone.seckill.mapper.OrderMapper;
import com.leone.seckill.vo.OrderItemVO;
import com.leone.seckill.vo.OrderVO;
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
     * @param goodsId
     * @param user
     * @return
     */
    @Transactional
    public OrderVO createOrder(Long goodsId, User user) {
        Goods goods = goodsService.findByGoodsId(goodsId);
        Order order = new Order();
        order.setCreateTime(new Date());
        order.setStatus(0);
        order.setUserId(user.getUserId());
        order.setTotalAmount(goods.getGoodsPrice());

        // 保存订单
        Long orderId = orderMapper.save(order);

        // 保存订单项
        OrderItem orderItem = orderDetailService.save(goods, orderId);
        OrderItemVO orderItemVO = new OrderItemVO();

        BeanUtils.copyProperties(orderItem, orderItemVO);

        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(order, orderVO);
        orderVO.setOrderItemVO(orderItemVO);
        orderVO.setOrderId(orderId);
        orderVO.setUsername(user.getUsername());
        orderVO.setPhone(user.getPhone());
        orderVO.setAddress(user.getAddress());

        // 保存用户秒杀到的订单到 redis 缓存中
        redisTemplate.opsForValue().set(RedisPrefix.ORDER_PREFIX + user.getUserId() + "-" + orderId, orderVO);
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
        return orderMapper.findByUserIdAndGoodsId(userId, goodsId);
    }
}
