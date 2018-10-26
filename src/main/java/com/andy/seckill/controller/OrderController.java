package com.andy.seckill.controller;

import com.andy.seckill.common.Response;
import com.andy.seckill.domain.Order;
import com.andy.seckill.service.GoodsService;
import com.andy.seckill.service.OrderService;
import com.andy.seckill.service.UserService;
import com.andy.seckill.vo.GoodsVO;
import com.andy.seckill.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

//	@Autowired
//    private UserService userService;
//
//	@Autowired
//	private OrderService orderService;
//
//	@Autowired
//    private GoodsService goodsService;
//
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
    
}
