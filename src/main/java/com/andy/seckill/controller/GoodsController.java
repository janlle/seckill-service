package com.andy.seckill.controller;

import com.andy.seckill.service.GoodsService;
import com.andy.seckill.vo.GoodsVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

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

//    public Response list() {
//        return Response.success(goodsService.list());
//    }


    @GetMapping("/list")
    public String list(Model model) {
        List<GoodsVO> goodsList = goodsService.list();
        model.addAttribute("goodsList", goodsList);
        return "goods_list";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam Long goodsId) {
        return "goods_detail";
    }


}
