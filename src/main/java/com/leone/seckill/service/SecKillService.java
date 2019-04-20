package com.leone.seckill.service;

import com.leone.seckill.common.RedisPrefix;
import com.leone.seckill.domain.User;
import com.leone.seckill.rabbitmq.RabbitMqSender;
import com.leone.seckill.rabbitmq.SecKillMessage;
import com.leone.seckill.vo.OrderVO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
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
     * @param goodsId
     * @param user
     */
    @Transactional
    public synchronized void kill(Long goodsId, User user) {
        boolean flag = goodsService.inventoryStock(goodsId);
        if (flag) {
            // 创建订单
            orderService.createOrder(goodsId, user);
        }
    }

    /**
     * 验证路径是否有误
     *
     * @param path
     * @return
     */
    public boolean checkPath(String path) {
        String result = (String) redisTemplate.opsForValue().get(RedisPrefix.SEC_KILL_PATH_PREFIX);
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
    public OrderVO result(Long userId, Long goodsId) {
        OrderVO orderVO = (OrderVO) redisTemplate.opsForValue().get(RedisPrefix.ORDER_PREFIX + userId + "-" + goodsId);
        if (ObjectUtils.isEmpty(orderVO)) {
            return null;
        }
        return orderVO;
    }

    /**
     * 发送消息到队列排队
     *
     * @param goodsId
     */
    public void sendQueue(Long goodsId, Long userId) {
        User user = userService.findOne(userId);
        if (ObjectUtils.isEmpty(user)) {
            return;
        }
        SecKillMessage message = new SecKillMessage(user, goodsId);
        rabbitMQSender.send(message);
    }


}
