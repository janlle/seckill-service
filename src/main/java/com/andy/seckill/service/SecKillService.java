package com.andy.seckill.service;

import com.andy.seckill.domain.User;
import com.andy.seckill.vo.GoodsDetailVO;
import com.andy.seckill.vo.GoodsVO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SecKillService {

    @Resource
    private OrderService orderService;

    @Resource
    private UserService userService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate redisTemplate;


    public void kill(User user, GoodsDetailVO goods) {


    }
}
