package com.xunji.server.service.impl;

import com.xunji.common.exception.LoginFailedException;
import com.xunji.pojo.dto.UserLoginDTO;
import com.xunji.pojo.entity.User;
import com.xunji.server.mapper.UserMapper;
import com.xunji.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * App登录（手机号+密码方式）
     */
    @Override
    public User appLogin(UserLoginDTO userLoginDTO) {
        String phone = userLoginDTO.getPhone();
        String password = userLoginDTO.getPassword();

        // 根据手机号和密码查询用户
        User user = userMapper.getByPhoneAndPassword(phone, password);

        // 如果用户不存在，抛出登录失败异常
        if(user == null){
            throw new LoginFailedException("手机号或密码错误");
        }

        return user;
    }
}

