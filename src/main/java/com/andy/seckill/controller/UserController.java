package com.andy.seckill.controller;

import com.andy.seckill.common.MessageEnum;
import com.andy.seckill.common.Response;
import com.andy.seckill.service.UserService;
import com.andy.seckill.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-10-26
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping
    public Response<UserVO> user(Model model, UserVO vo) {
        return Response.success(vo);
    }

    @GetMapping("/login")
    public String toLogin() {
        return "login";
    }

    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }

    @ResponseBody
    @GetMapping("/hello")
    public Response hello() {
        return Response.success(MessageEnum.SUCCESS);
    }

}
