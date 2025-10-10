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
}
