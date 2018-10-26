package com.andy.seckill.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SecKillService {

    @Resource
    private OrderService orderService;

    @Resource
    private UserService userService;

}
