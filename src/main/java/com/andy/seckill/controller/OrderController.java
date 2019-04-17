package com.andy.seckill.controller;

import com.andy.seckill.service.OrderService;
import com.andy.seckill.vo.OrderItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/detail/{orderId}")
    public String detail(Model model, @PathVariable("orderId") Long orderId) {
        OrderItemVO orderDetailVO = orderService.findOne(orderId);
        model.addAttribute("orderDetail", orderDetailVO);
        return "order_detail";
    }

    @PostMapping("/create")
    public String create() {

        return null;
    }

    @PostMapping("/finish")
    public String finish() {

        return null;
    }


}
