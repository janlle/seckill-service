package com.leone.seckill.rabbitmq;


import com.leone.seckill.domain.User;

import java.io.Serializable;

/**
 * <p>
 *
 * @author leone
 * @since 2018-11-05
 **/
public class SecKillMessage implements Serializable {

    private static final long serialVersionUID = -157748461550405224L;

    private User user;

	private Long goodsId;

    public SecKillMessage(User user, Long goodsId) {
        this.user = user;
        this.goodsId = goodsId;
    }

    public SecKillMessage() {
    }

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
