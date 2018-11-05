package com.andy.seckill.controller;

import com.andy.seckill.common.Response;
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

//    @GetMapping("/detail")
//    public Response<GoodsVO> info(Model model, @RequestParam("orderId") long orderId) {
//    	Order order = orderService.getOrderById(orderId);
//    	if(order == null) {
//    		return Response.error("error");
//    	}
//    	long goodsId = order.getGoodsId();
//    	GoodsVO goodsVO = goodsService.getGoodsVoByGoodsId(goodsId);
//    	return Response.success(goodsVO);
//    }

    @GetMapping("/detail")
    public Response detail(Model model, @RequestParam("orderId") Long orderId) {
        OrderDetailVO order = orderService.findOne(orderId);
        return Response.success(null);
    }

}
