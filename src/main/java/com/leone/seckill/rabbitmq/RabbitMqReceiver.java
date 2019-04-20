package com.leone.seckill.rabbitmq;

import com.leone.seckill.domain.User;
import com.leone.seckill.service.GoodsService;
import com.leone.seckill.service.OrderService;
import com.leone.seckill.service.SecKillService;
import com.leone.seckill.vo.GoodsVO;
import com.leone.seckill.vo.OrderVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * @author leone
 * @since 2018-11-05
 **/
@Slf4j
@Component
public class RabbitMqReceiver {

    private OrderService orderService;

    private GoodsService goodsService;

    private SecKillService secKillService;

    private ObjectMapper objectMapper;

    @Autowired
    public RabbitMqReceiver(OrderService orderService,
                            GoodsService goodsService,
                            SecKillService secKillService,
                            ObjectMapper objectMapper) {
        this.orderService = orderService;
        this.goodsService = goodsService;
        this.secKillService = secKillService;
        this.objectMapper = objectMapper;
    }

    /**
     * 1.判断商品库存
     * 2.判断用户是否秒杀到
     *
     * @param message
     */
    @RabbitListener(queues = RabbitMqConfig.SEC_KILL_QUEUE)
    public void receive(String message) throws Exception {
        log.info("receive message: {}", message);
        SecKillMessage secKillMessage = objectMapper.readValue(message, SecKillMessage.class);
        User user = secKillMessage.getUser();
        Long goodsId = secKillMessage.getGoodsId();

        // 查找商品判断库存情况
        GoodsVO goods = goodsService.findOne(goodsId);
        int stock = goods.getGoodsStock();
        if (stock <= 0) {
            return;
        }

        // 校验用户是否秒杀到同一款商品
        OrderVO order = orderService.findByUserIdAndGoodsId(user.getUserId(), goodsId);
        if (order != null) {
            return;
        }

        // 减库存，创建订单
        secKillService.kill(goodsId, user);
    }


}
