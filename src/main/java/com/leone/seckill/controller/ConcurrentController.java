package com.leone.seckill.controller;

import com.leone.seckill.common.RedisPrefix;
import com.leone.seckill.domain.User;
import com.leone.seckill.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

/**
 * <p> 并发接口测试
 *
 * @author leone
 * @since 2019-04-17
 **/
@Slf4j
@RestController
@RequestMapping("/api")
public class ConcurrentController {

    private Long offset = 0L;

    @Resource
    private Random random;


    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/concurrent/empty")
    public void concurrentEmpty() {
        log.info("offset: {}", offset);
        offset++;
    }

    @GetMapping("/concurrent/redis")
    public String concurrentRedis() {
        log.info("offset: {}", offset);
        offset++;
        return (String) redisTemplate.opsForValue().get(RedisPrefix.SEC_KILL_PATH_PREFIX);
    }

    @GetMapping("/concurrent/mysql")
    public User concurrentMysql(@RequestParam Long userId) {
        log.info("offset: {}", offset);
        offset++;
        return userService.findOne(userId + random.nextInt(3));
    }


}
