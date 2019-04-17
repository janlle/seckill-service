package com.andy.seckill.mapper;

import com.andy.seckill.domain.Order;
import com.andy.seckill.vo.OrderVO;
import org.apache.ibatis.annotations.*;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-10-26
 **/
@Mapper
public interface OrderMapper {
	
	@Select("select * from t_order where user_id=#{userId} and goods_id=#{goodsId}")
	OrderVO findByUserIdAndOrderId(@Param("userId") Long userId, @Param("goodsId") Long goodsId);

    @Select("select * from t_order where order_id = #{orderId}")
    OrderVO findOne(@Param("orderId") Long orderId);

    @Insert("insert into t_order(user_id,total_price,status,create_time,pay_time) values(#{order.userId},#{order.totalPrice},#{order.status},#{order.createTime},#{order.payTime})")
    @SelectKey(keyColumn = "order_id", keyProperty = "orderId", resultType = Long.class, before = false, statement = "select last_insert_id()")
    Long save(@Param("order")Order order);

}
