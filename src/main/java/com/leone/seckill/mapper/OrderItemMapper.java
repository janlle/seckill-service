package com.leone.seckill.mapper;

import com.leone.seckill.domain.OrderItem;
import com.leone.seckill.vo.OrderVO;
import org.apache.ibatis.annotations.*;

/**
 * <p>电商秒杀业务中每个用户一件商品只可以秒杀一次，所以不会存在一个用户有秒杀到两件商品的情况
 *
 * @author leone
 * @since 2018-10-26
 **/
@Mapper
public interface OrderItemMapper {

    @Insert("insert into t_order_item(goods_id, order_id, goods_picture, goods_price, goods_count, goods_description, goods_title, create_time)" +
                             " values(#{item.goodsId}, #{item.orderId}, #{item.goodsPicture}, #{item.goodsPrice}, #{item.goodsCount}, #{item.goodsDescription}, #{item.goodsTitle}, #{item.createTime})")
    @SelectKey(keyColumn = "order_item_id", keyProperty = "orderItemId", resultType = Long.class, before = false, statement = "select last_insert_id()")
    Long save(@Param("item") OrderItem orderItem);

    @Select("select u.username, u.address, u.phone, o.*, oi.* from t_user u left join t_order_item oi on u.user_id = oi.user_id left join t_order o on o.order_id = oi.order_id where u.user_id = #{userId} and o.goods_id = #{goodsId}")
    OrderVO findByUserIdAndGoodsId(@Param("userId") Long userId, @Param("goodsId") Long goodsId);


}
