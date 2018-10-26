package com.andy.seckill.mapper;

import com.andy.seckill.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-10-26
 **/
@Mapper
public interface UserMapper {

    @Select("select * from t_user where user_id = #{userId}")
    UserVO findUserById(@Param("userId") Long userId);

    @Update("update t_user set password = #{password} where id = #{id}")
    void update(UserVO userVO);
}
