package com.andy.seckill.service;

import com.andy.seckill.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private OrderService orderService;

    @Resource
    private GoodsService goodsService;

}
