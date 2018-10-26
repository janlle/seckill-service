package com.andy.seckill.controller;

import com.andy.seckill.common.Response;
import com.andy.seckill.service.GoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-10-26
 **/
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    public Response list() {
        return Response.success(goodsService.list());
    }

}
