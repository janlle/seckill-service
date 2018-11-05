package com.andy.seckill.rabbitmq;

import com.andy.seckill.domain.Order;
import com.andy.seckill.domain.User;
import com.andy.seckill.service.GoodsService;
import com.andy.seckill.service.OrderService;
import com.andy.seckill.service.SecKillService;
import com.andy.seckill.vo.GoodsDetailVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-11-05
 **/
@Slf4j
@Component
public class RabbitMQReceiver {

    private OrderService orderService;

    private GoodsService goodsService;

    private SecKillService secKillService;

    private ObjectMapper objectMapper;

    @Autowired
    public RabbitMQReceiver(OrderService orderService,
                            GoodsService goodsService,
                            SecKillService secKillService,
                            ObjectMapper objectMapper) {
        this.orderService = orderService;
        this.goodsService = goodsService;
        this.secKillService = secKillService;
        this.objectMapper = objectMapper;
    }

    /**
     * 接受消息
     *
     * @param message
     */
    @RabbitListener(queues = RabbitMQConfig.SEC_KILL_QUEUE)
    public void receive(String message) throws Exception {
        log.info("receive message:{}" + message);
        SecKillMessage secKillMessage = objectMapper.readValue(message, SecKillMessage.class);
        User user = secKillMessage.getUser();
        Long goodsId = secKillMessage.getGoodsId();

        GoodsDetailVO goods = goodsService.findOne(goodsId);
        int stock = goods.getStock();
        if (stock <= 0) {
            return;
        }
        //判断是否已经秒杀到了
        Order order = orderService.findByUserIdAndGoodsId(user.getUserId(), goodsId);
        if (order != null) {
            return;
        }
        //减库存 下订单 写入秒杀订单
        secKillService.kill(user, goods);
    }


}
