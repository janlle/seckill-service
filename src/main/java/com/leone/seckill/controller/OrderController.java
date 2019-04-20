package com.leone.seckill.controller;

import com.leone.seckill.common.VersionFlag;
import com.leone.seckill.service.OrderService;
import com.leone.seckill.vo.OrderVO;
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

    @VersionFlag(version = "v1.0.0")
    @GetMapping("/{orderId}")
    public String findOne(Model model, @PathVariable("orderId") Long orderId) {
        OrderVO orderVO = orderService.findOne(orderId);
        model.addAttribute("order", orderVO);
        return "order_detail";
    }


}
