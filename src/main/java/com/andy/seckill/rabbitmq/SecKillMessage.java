package com.andy.seckill.rabbitmq;


import com.andy.seckill.domain.User;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-11-05
 **/
public class SecKillMessage {

	private User user;

	private Long goodsId;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
}
