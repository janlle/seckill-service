package com.leone.seckill.vo;

/**
 * <p>
 *
 * @author leone
 * @since 2019-04-18
 **/
public class SecKillVO {

    private Long goodsId;

    private Long userId;

    public SecKillVO() {
    }

    public SecKillVO(Long goodsId, Long userId) {
        this.goodsId = goodsId;
        this.userId = userId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
