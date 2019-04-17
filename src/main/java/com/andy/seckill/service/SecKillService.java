package com.andy.seckill.service;

import com.andy.seckill.common.RedisPrefix;
import com.andy.seckill.domain.User;
import com.andy.seckill.rabbitmq.RabbitMqSender;
import com.andy.seckill.rabbitmq.SecKillMessage;
import com.andy.seckill.vo.OrderAddVO;
import com.andy.seckill.vo.OrderItemVO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
public class SecKillService {

    @Resource
    private OrderService orderService;

    @Resource
    private GoodsService goodsService;

    @Resource
    private UserService userService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private RabbitMqSender rabbitMQSender;


    /**
     * 秒杀业务
     * 1.判断商品库存
     * 2.调用订单service
     *
     * @param orderAddVO
     */
    @Transactional
    public OrderItemVO kill(OrderAddVO orderAddVO) {
        boolean flag = goodsService.inventoryStock(orderAddVO.getGoodsId());
        if (flag) {
            // 生成订单
            return orderService.createOrder(orderAddVO);
        } else {
            return null;
        }


    }

    /**
     * 验证路径是否有误
     *
     * @param path
     * @return
     */
    public boolean checkPath(String path) {
        String result = (String) redisTemplate.opsForValue().get(RedisPrefix.KILL_PATH);
        if (StringUtils.isEmpty(path)) {
            return false;
        }
        return path.equals(result);
    }

    /**
     * 获得秒杀结果
     *
     * @param userId
     * @param goodsId
     * @return
     */
    public Long result(Long userId, Long goodsId) {

        return null;
    }

    /**
     * 发送消息到队列排队
     *
     * @param goodsId
     */
    public void sendQueue(Long goodsId) {
        SecKillMessage message = new SecKillMessage(new User(), goodsId);
        rabbitMQSender.send(message);
    }


}
