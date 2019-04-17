package com.andy.seckill.mapper;

import com.andy.seckill.domain.OrderItem;
import com.andy.seckill.vo.OrderVO;
import org.apache.ibatis.annotations.*;

/**
 * <p>电商秒杀业务中每个用户一件商品只可以秒杀一次，所以不会存在一个用户有秒杀到两件商品的情况
 *
 * @author Leone
 * @since 2018-10-26
 **/
@Mapper
public interface OrderItemMapper {

    @Insert("insert into t_order_detail(goods_id, order_id, picture, price, count, total, create_time) values(orderDetail.goodsId, orderDetail.orderId, orderDetail.picture, orderDetail.price, orderDetail.count, orderDetail.total, orderDetail.createTime)")
    @SelectKey(keyColumn = "order_detail_id", keyProperty = "orderDetailId", resultType = Long.class, before = false, statement = "select last_insert_id()")
    int save(@Param("orderDetail") OrderItem orderDetail);

    @Select("select u.username, u.address, u.phone, o.*, oi.* from t_user u left join t_order_item oi on u.user_id = oi.user_id left join t_order o on o.order_id = oi.order_id where u.user_id = #{userId} and o.goods_id = #{goodsId}")
    OrderVO findByUserIdAndGoodsId(@Param("userId") Long userId, @Param("goodsId") Long goodsId);


}