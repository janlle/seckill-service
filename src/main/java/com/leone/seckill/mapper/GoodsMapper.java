package com.leone.seckill.mapper;

import com.leone.seckill.domain.Goods;
import com.leone.seckill.vo.GoodsVO;
import com.leone.seckill.vo.GoodsListVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *
 * @author leone
 * @since 2018-10-26
 **/
@Mapper
public interface GoodsMapper {

    // @Results({@Result(id=true,column="a",property="b")})配置和列名和不同的属性名
    @Select("select * from t_goods where goods_status = 0")
    List<GoodsListVO> list();

	@Select("select * from t_goods where goods_status = 0 and goods_id = #{goodsId}")
    GoodsVO findById(@Param("goodsId") Long goodsId);

    @Select("select * from t_goods where goods_status = 0 and goods_id = #{goodsId}")
    Goods findByGoodsId(@Param("goodsId") Long goodsId);

	@Update("update t_goods set goods_stock = goods_stock - 1 where goods_id = #{goodsId} and goods_stock > 0 and goods_status = 0")
	int inventoryStock(@Param("goodsId") Long goodsId);

}
