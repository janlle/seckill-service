package com.andy.seckill.controller;


import com.andy.seckill.common.RedisPrefix;
import com.andy.seckill.common.Result;
import com.andy.seckill.domain.User;
import com.andy.seckill.exception.ExceptionMessage;
import com.andy.seckill.rabbitmq.RabbitMqSender;
import com.andy.seckill.service.GoodsService;
import com.andy.seckill.service.OrderService;
import com.andy.seckill.service.SecKillService;
import com.andy.seckill.vo.GoodsListVO;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-11-05
 **/
@Controller
@RequestMapping("/api")
public class SecKillController implements InitializingBean {

    @Resource
    private SecKillService secKillService;

    @Resource
    private GoodsService goodsService;

    @Resource
    private OrderService orderService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private RabbitMqSender rabbitMQSender;

    private HashMap<Long, Boolean> localOverMap = new HashMap<>();

    /**
     * 初始化商品数据到 redis 中和本地 map 中
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<GoodsListVO> list = goodsService.list();
        if (ObjectUtils.isEmpty(list)) {
            return;
        } else {
            list.forEach(e -> {
                redisTemplate.opsForValue().set(RedisPrefix.GOODS_PREFIX + e.getGoodsId(), e.getGoodsStock());
                localOverMap.put(e.getGoodsId(), false);
            });
        }
        String path = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set(RedisPrefix.SEC_KILL_PATH_PREFIX, path);
    }


    @PostMapping(value = "/{path}/kill")
    public Result secKill(Model model, @RequestParam Long goodsId, @RequestParam Long userId, @PathVariable("path") String path) {

        // 验证path
        boolean check = secKillService.checkPath(path);
        if (!check) {
            return Result.error(ExceptionMessage.SEC_KILL_PATH_FAIL);
        }

        // 内存标记，减少redis访问
        if (localOverMap.get(goodsId)) {
            return Result.error(ExceptionMessage.GOODS_EMPTY);
        }

        // 预减库存
        Long goodsCount = (Long) redisTemplate.opsForValue().get(RedisPrefix.GOODS_PREFIX + goodsId);

        if (!ObjectUtils.isEmpty(goodsCount) && goodsCount < 0) {
            localOverMap.put(goodsId, true);
            return Result.error(ExceptionMessage.GOODS_EMPTY);
        }

        // 判断用户是否已经秒杀到了
//        OrderVO order = orderService.findByUserIdAndGoodsId(userId, goodsId);
//        if (order != null) {
//            return Result.build(MessageEnum.ERROR);
//        }

        // 进入消息队列排队中
        secKillService.sendQueue(goodsId);
        return Result.success(0);
    }


    /**
     * 0：排队中，1：成功，-1：秒杀失败
     */
    @ResponseBody
    @GetMapping("/kill/result")
    public Result<Long> result(Model model, @RequestParam Long userId, @RequestParam Long goodsId) {
        model.addAttribute("user", new User());
        Long result = secKillService.result(userId, goodsId);
        return Result.success(result);
    }

    @ResponseBody
    @GetMapping("/kill/path")
    public String path() {
        return Objects.requireNonNull(redisTemplate.opsForValue().get(RedisPrefix.SEC_KILL_PATH_PREFIX)).toString();
    }


}
