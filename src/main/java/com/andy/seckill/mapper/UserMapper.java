package com.andy.seckill.mapper;

import com.andy.seckill.domain.User;
import com.andy.seckill.vo.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    @Select("select * from t_user where account = #{account} and password = ${password}")
    UserVO findUserByAccountAndPassword(@Param("account") String account, @Param("password") String password);

    @Select("select * from t_user where user_id = #{userId}")
    User findOne(@Param("userId")Long userId);

    @Insert("insert into t_user(account, username, password, address, age, phone, create_time, deleted) values(#{user.account}, #{user.username}, #{user.password}, #{user.address}, #{user.age}, #{user.phone}, #{user.createTime}, #{user.deleted})")
    void save(@Param("user") User user);



}
