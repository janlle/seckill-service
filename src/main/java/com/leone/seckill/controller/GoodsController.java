package com.leone.seckill.controller;

import com.leone.seckill.common.Result;
import com.leone.seckill.service.GoodsService;
import com.leone.seckill.vo.GoodsListVO;
import com.leone.seckill.vo.GoodsVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *
 * @author leone
 * @since 2018-10-26
 **/
@Controller
@RequestMapping("/api/goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    @GetMapping("/list")
    public String list(Model model) {
        List<GoodsListVO> goodsList = goodsService.list();
        model.addAttribute("goodsList", goodsList);
        return "goods_list";
    }

    @ResponseBody
    @GetMapping("/{goodsId}")
    public Result<GoodsVO> findOne(@PathVariable Long goodsId,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {

        GoodsVO goodsVO = goodsService.findOne(goodsId);

        long startTime = goodsVO.getStartTime().getTime();
        long endTime = goodsVO.getEndTime().getTime();
        long nowTime = System.currentTimeMillis();

        int secKillStatus;
        int remainSeconds;

        // 秒杀还没开始，倒计时
        if (nowTime < startTime) {
            secKillStatus = 0;
            remainSeconds = (int) ((startTime - nowTime) / 1000);

            // 秒杀已经结束
        } else if (nowTime > endTime) {
            secKillStatus = 2;
            remainSeconds = -1;
            // 秒杀进行中
        } else {
            secKillStatus = 1;
            remainSeconds = 0;
        }

        goodsVO.setSecKillStatus(secKillStatus);
        goodsVO.setRemainSeconds(remainSeconds);
        return Result.success(goodsVO);
    }


}
