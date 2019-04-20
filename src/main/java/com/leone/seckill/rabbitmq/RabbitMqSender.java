package com.leone.seckill.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>
 *
 * @author leone
 * @since 2018-11-05
 **/
@Slf4j
@Component
public class RabbitMqSender {

    @Resource
    private AmqpTemplate amqpTemplate;

    @Resource
    private ObjectMapper objectMapper;

    /**
     * 发送消息
     *
     * @param message
     * @throws Exception
     */
    public void send(SecKillMessage message) {
        try {
            String msg = objectMapper.writeValueAsString(message);
            amqpTemplate.convertAndSend(RabbitMqConfig.SEC_KILL_QUEUE, msg);
            log.info("send message: {}", msg);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


}
