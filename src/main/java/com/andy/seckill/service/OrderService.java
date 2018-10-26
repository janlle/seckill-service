package com.andy.seckill.service;

import com.andy.seckill.mapper.OrderMapper;
import com.andy.seckill.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderService {

    @Resource
    private OrderMapper orderMapper;

}
