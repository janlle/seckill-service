package com.leone.seckill.mapper;

import com.leone.seckill.domain.Order;
import com.leone.seckill.vo.OrderVO;
import org.apache.ibatis.annotations.*;

/**
 * <p>
 *
 * @author leone
 * @since 2018-10-26
 **/
@Mapper
public interface OrderMapper {
	
    @Select("select u.username, u.address, u.phone, o.*, oi.* from t_user u left join t_order o on u.user_id = o.user_id left join t_order_item oi on o.order_id = oi.order_id where u.user_id = #{userId} and oi.goods_id = #{goodsId}")
    OrderVO findByUserIdAndGoodsId(@Param("userId") Long userId, @Param("goodsId") Long goodsId);

    @Select("select * from t_order where order_id = #{orderId}")
    OrderVO findOne(@Param("orderId") Long orderId);

    @Insert("insert into t_order(user_id, total_amount, status, create_time, pay_time) values(#{order.userId}, #{order.totalAmount}, #{order.status}, #{order.createTime}, #{order.payTime})")
    @SelectKey(keyColumn = "order_id", keyProperty = "orderId", resultType = Long.class, before = false, statement = "select last_insert_id()")
    Long save(@Param("order") Order order);

}
