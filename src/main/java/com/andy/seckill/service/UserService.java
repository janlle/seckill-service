package com.andy.seckill.service;

import com.andy.seckill.domain.User;
import com.andy.seckill.mapper.UserMapper;
import com.andy.seckill.vo.UserVO;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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

    public User findOne(Long userId) {
        User user = userMapper.findOne(userId);
        Assert.notNull(user, "用户不存在");
        return user;
    }
}
