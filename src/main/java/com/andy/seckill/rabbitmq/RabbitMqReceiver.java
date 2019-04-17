package com.andy.seckill.rabbitmq;

import com.andy.seckill.domain.User;
import com.andy.seckill.service.GoodsService;
import com.andy.seckill.service.OrderService;
import com.andy.seckill.service.SecKillService;
import com.andy.seckill.vo.GoodsDetailVO;
import com.andy.seckill.vo.OrderAddVO;
import com.andy.seckill.vo.OrderVO;
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
        log.info("receive message: {}" + message);
        SecKillMessage secKillMessage = objectMapper.readValue(message, SecKillMessage.class);
        User user = secKillMessage.getUser();
        Long goodsId = secKillMessage.getGoodsId();

        // 查找商品判断库存情况
        GoodsDetailVO goods = goodsService.findOne(goodsId);
        int stock = goods.getGoodsStock();
        if (stock <= 0) {
            return;
        }

        // 判断是否已经秒杀到了
        OrderVO order = orderService.findByUserIdAndGoodsId(user.getUserId(), goodsId);
        if (order != null) {
            return;
        }

        OrderAddVO orderAddVO = new OrderAddVO(user.getUserId(),goodsId);

        // 减库存，创建订单
        secKillService.kill(orderAddVO);
    }


}
