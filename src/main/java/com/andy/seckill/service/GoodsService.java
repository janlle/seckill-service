package com.andy.seckill.service;

import com.andy.seckill.mapper.GoodsMapper;
import com.andy.seckill.vo.GoodsDetailVO;
import com.andy.seckill.vo.GoodsVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-10-26
 **/
@Service
public class GoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private OrderService orderService;

    @Resource
    private UserService userService;


    /**
     * @return
     */
    public List<GoodsVO> list() {
        return goodsMapper.list();
    }


    /**
     * @param goodsId
     * @return
     */
    public GoodsDetailVO findOne(Long goodsId) {
        return goodsMapper.findById(goodsId);
    }

    /**
     * 减库存
     *
     * @param goodsId
     * @return
     */
    public boolean inventoryStock(Long goodsId) {
        return goodsMapper.inventoryStock(goodsId) > 0;
    }


}
