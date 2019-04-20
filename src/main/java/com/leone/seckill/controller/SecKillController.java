package com.leone.seckill.controller;


import com.leone.seckill.common.RedisPrefix;
import com.leone.seckill.common.Result;
import com.leone.seckill.common.VersionFlag;
import com.leone.seckill.exception.ExceptionMessage;
import com.leone.seckill.service.GoodsService;
import com.leone.seckill.service.SecKillService;
import com.leone.seckill.vo.GoodsListVO;
import com.leone.seckill.vo.OrderVO;
import com.leone.seckill.vo.SecKillVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
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
 * @author leone
 * @since 2018-11-05
 **/
@Slf4j
@Controller
@RequestMapping("/api")
public class SecKillController implements InitializingBean {

    @Resource
    private SecKillService secKillService;

    @Resource
    private GoodsService goodsService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    // 用来过滤商品是否存在或是否秒杀完毕
    private HashMap<Long, Boolean> localOverMap = new HashMap<>();

    /**
     * 初始化商品数据到 redis 中和本地 map 中
     */
    @Override
    public void afterPropertiesSet() {
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


    @VersionFlag(version = "v2.0")
    @ResponseBody
    @PostMapping(value = "/{path}/kill")
    public Result secKill(@RequestBody SecKillVO secKillVO, @PathVariable("path") String path) {
        // 验证path
        boolean check = secKillService.checkPath(path);
        if (!check) {
            return Result.error(ExceptionMessage.SEC_KILL_PATH_FAIL);
        }

        // 验证商品是否在被秒杀，或商品是否被秒杀完
        Boolean flag = localOverMap.get(secKillVO.getGoodsId());
        if (!ObjectUtils.isEmpty(flag) && flag) {
            log.info(ExceptionMessage.NOT_GOODS_KILL_OR_GOODS_EMPTY.getMessage());
            return Result.error(ExceptionMessage.NOT_GOODS_KILL_OR_GOODS_EMPTY);
        }

        // 查询redis中商品库存
        Integer goodsCount = (Integer) redisTemplate.opsForValue().get(RedisPrefix.GOODS_PREFIX + secKillVO.getGoodsId());

        // 在缓存中预减库存
        if (!ObjectUtils.isEmpty(goodsCount)) {
            if (goodsCount > 0) {
                log.info("预减redis中商品数量 goodsId: {} userId: {} goodsCount: {}", secKillVO.getGoodsId(), secKillVO.getUserId(), goodsCount - 1);
                redisTemplate.opsForValue().set(RedisPrefix.GOODS_PREFIX + secKillVO.getGoodsId(), goodsCount - 1);
            } else {
                localOverMap.put(secKillVO.getGoodsId(), true);
                log.info("设置内存缓存中商品标记为不可被秒杀 goodsId: {} goodsCount: {}", secKillVO.getGoodsId(), goodsCount);
                return Result.error(ExceptionMessage.GOODS_EMPTY);
            }
        }

        // 进入消息队列排队中
        secKillService.sendQueue(secKillVO.getGoodsId(), secKillVO.getUserId());
        return Result.success(ExceptionMessage.SEC_KILL_SUCCESS.getMessage());
    }


    /**
     * code 10000：排队中，20000：成功，40000：秒杀失败
     */
    @ResponseBody
    @GetMapping("/kill/result")
    public Result result(@RequestParam Long userId, @RequestParam Long goodsId) {
        OrderVO result = secKillService.result(userId, goodsId);
        if (ObjectUtils.isEmpty(result)) {
            return Result.error(ExceptionMessage.SEC_KILL_ERROR);
        }
        return Result.success(result);
    }


    @ResponseBody
    @GetMapping("/kill/path")
    public Result path() {
        return Result.success(Objects.requireNonNull(redisTemplate.opsForValue().get(RedisPrefix.SEC_KILL_PATH_PREFIX)).toString());
    }


}
