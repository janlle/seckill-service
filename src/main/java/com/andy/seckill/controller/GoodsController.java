package com.andy.seckill.controller;

import com.andy.seckill.common.Result;
import com.andy.seckill.service.GoodsService;
import com.andy.seckill.vo.GoodsListVO;
import com.andy.seckill.vo.GoodsVO;
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
 * @author Leone
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
    public Result detail(@PathVariable Long goodsId,
                         HttpServletRequest request,
                         HttpServletResponse response,
                         Model model) {

        GoodsVO goodsDetailVO = goodsService.findOne(goodsId);
        model.addAttribute("goods", goodsDetailVO);

        long startTime = goodsDetailVO.getStartTime().getTime();
        long endTime = goodsDetailVO.getEndTime().getTime();
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
//        model.addAttribute("secKillStatus", secKillStatus);
//        model.addAttribute("remainSeconds", remainSeconds);
//        return "goods_detail";
        goodsDetailVO.setSecKillStatus(secKillStatus);
        goodsDetailVO.setRemainSeconds(remainSeconds);
        return Result.success(goodsDetailVO);
    }


}
