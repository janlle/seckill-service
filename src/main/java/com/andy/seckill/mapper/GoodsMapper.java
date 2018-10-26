package com.andy.seckill.mapper;

import com.andy.seckill.vo.GoodsVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-10-26
 **/
@Mapper
public interface GoodsMapper {
	
	@Select("select * from t_goods where status = 0")
    //@Results(value={@Result(column="a",property="b")})配置和列名和不同的属性名
	List<GoodsVO> list();

	@Select("select * from t_goods where status = 0 and goods_id = #{goodsId}")
	GoodsVO findById(@Param("goodsId") Long goodsId);

	@Update("update t_goods set stock_count = stock_count - 1 where goods_id = #{goodsId} and stock_count > 0")
	int reduceStock(@Param("goodsId") Long goodsId);

}
