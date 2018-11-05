package com.andy.seckill.mapper;

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


//	@Insert("insert into order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date)values("
//			+ "#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate} )")
//	@SelectKey(keyColumn="id", keyProperty="id", resultType=long.class, before=false, statement="select last_insert_id()")
//    Integer insert(OrderVO orderVO);

//	@Insert("insert into t_order(user_id, goods_id, order_id)values(#{userId}, #{goodsId}, #{orderId})")
//	Integer insert(OrderVO orderVO);




	
}
