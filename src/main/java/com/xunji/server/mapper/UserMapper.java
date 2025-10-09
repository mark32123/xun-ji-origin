package com.xunji.server.mapper;

import com.xunji.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    /**
     * 根据用户名查询用户信息
     *
     * @param name 用户名
     * @return 用户信息
     */
    @Select("SELECT * FROM user WHERE name = #{name}")
    User findByName(String name);
}
