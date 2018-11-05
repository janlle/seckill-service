package com.andy.seckill.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-11-05
 **/
@Configuration
public class RabbitMQConfig {

    public static final String SEC_KILL_QUEUE = "sec-kill-queue";


    public static final String TOPIC_EXCHANGE = "topic-exchange";

    public static final String FANOUT_EXCHANGE = "fanout-exchange";

    public static final String HEADERS_EXCHANGE = "headers-exchange";

    public static final String DIRECT_EXCHANGE = "direct-exchange";



    @Bean
    public Queue secKillQueue() {
        return new Queue(SEC_KILL_QUEUE, true);
    }


    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }


    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(secKillQueue()).to(topicExchange()).with("topic.key1");
    }
    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(secKillQueue()).to(topicExchange()).with("topic.#");
    }


}
