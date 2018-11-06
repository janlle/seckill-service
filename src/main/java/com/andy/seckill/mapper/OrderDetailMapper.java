package com.andy.seckill.mapper;

import com.andy.seckill.domain.OrderDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectKey;

/**
 * @author Leone
 * @since 2018-10-26
 **/
@Mapper
public interface OrderDetailMapper {

    @Insert("insert into t_order_detail(goods_id, order_id, picture, price, count, total, create_time) values(orderDetail.goodsId, orderDetail.orderId, orderDetail.picture, orderDetail.price, orderDetail.count, orderDetail.total, orderDetail.createTime)")
    @SelectKey(keyColumn = "order_detail_id", keyProperty = "orderDetailId", resultType = Long.class, before = false, statement = "select last_insert_id()")
    int save(@Param("orderDetail") OrderDetail orderDetail);

}
