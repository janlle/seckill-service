package com.andy.seckill.service;

import com.andy.seckill.mapper.UserMapper;
import com.andy.seckill.vo.UserVO;
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


    public UserVO login(String account, String password) {
        return userMapper.findUserByAccountAndPassword(account, password);
    }

}
