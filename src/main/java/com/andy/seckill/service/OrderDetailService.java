package com.andy.seckill.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-10-26
 **/
@Service
public class OrderDetailService {

    @Resource
    private OrderService orderService;

    @Resource
    private UserService userService;

}
