package com.leone.seckill.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * <p>
 *
 * @author leone
 * @since 2019-04-17
 **/
@Configuration
public class AppConfig {

    @Bean
    public Random random() {
        return new Random();
    }

}
