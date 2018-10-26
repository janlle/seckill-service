package com.andy.seckill.controller;

import com.andy.seckill.common.Response;
import com.andy.seckill.service.UserService;
import com.andy.seckill.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-10-26
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Response<UserVO> user(Model model, UserVO vo) {
        return Response.success(vo);
    }

}
