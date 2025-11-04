package com.xunji.server.mapper;

import com.xunji.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    /**
     * 根据手机号和密码查询用户
     * @param phone
     * @param password
     * @return
     */
    @Select("select * from user where phone = #{phone} and password = #{password}")
    User getByPhoneAndPassword(String phone, String password);

    /**
     * 新增用户
     * @param newUser
     */
    @Select("insert into user(phone, password, name, sex, create_time) values(#{phone}, #{password}, #{name}, #{sex}, #{createTime})")
    void insertNewUser(User newUser);
}
