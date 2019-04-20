package com.leone.seckill.service;

import com.leone.seckill.domain.Goods;
import com.leone.seckill.mapper.GoodsMapper;
import com.leone.seckill.vo.GoodsListVO;
import com.leone.seckill.vo.GoodsVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *
 * @author leone
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
     * 获取所有商品类表
     *
     * @return
     */
    public List<GoodsListVO> list() {
        return goodsMapper.list();
    }


    /**
     * 根据商品id查找商品
     *
     * @param goodsId
     * @return
     */
    public GoodsVO findOne(Long goodsId) {
        return goodsMapper.findById(goodsId);
    }

    /**
     * 根据商品id查找商品
     *
     * @param goodsId
     * @return
     */
    public Goods findByGoodsId(Long goodsId) {
        return goodsMapper.findByGoodsId(goodsId);
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
