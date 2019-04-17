package com.andy.seckill.controller;

import com.andy.seckill.service.OrderService;
import com.andy.seckill.vo.OrderItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *
 * @author leone
 **/
@Controller
@RequestMapping("/api/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{orderId}")
    public String findOne(Model model, @PathVariable("orderId") Long orderId) {
        OrderItemVO orderDetailVO = orderService.findOne(orderId);
        model.addAttribute("orderDetail", orderDetailVO);
        return "order_detail";
    }

}
