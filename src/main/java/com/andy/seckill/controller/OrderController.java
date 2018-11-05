package com.andy.seckill.controller;

import com.andy.seckill.service.OrderService;
import com.andy.seckill.service.UserService;
import com.andy.seckill.vo.OrderDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private UserService userService;

    private OrderService orderService;

    @Autowired
    public OrderController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/detail")
    public String detail(Model model, @RequestParam("orderId") Long orderId) {
        OrderDetailVO orderDetailVO = orderService.findOne(orderId);
        model.addAttribute("orderDetail", orderDetailVO);
        return "order_detail";
    }


}
