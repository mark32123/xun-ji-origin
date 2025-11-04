package com.xunji.server.service.impl;

import com.xunji.common.exception.LoginFailedException;
import com.xunji.common.result.Result;
import com.xunji.pojo.dto.UserLoginDTO;
import com.xunji.pojo.dto.UserRegisterDTO;
import com.xunji.pojo.entity.User;
import com.xunji.server.mapper.UserMapper;
import com.xunji.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * App登录（手机号+密码方式）
     */
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

    /**
     * App用户注册
     * @param userRegisterDTO 用户注册信息
     * @return
     */
    public Result register(UserRegisterDTO userRegisterDTO) {
        //检查用户是否注册
        String phone = userRegisterDTO.getPhone();
        String password = userRegisterDTO.getPassword();
        User user = userMapper.getByPhoneAndPassword(phone, password);
        if(user != null){
            return Result.error("用户已存在");
        }

        //创建新用户
        User newUser = new User();
        newUser.setPhone(phone);
        newUser.setPassword(password);
        newUser.setName(userRegisterDTO.getName());
        newUser.setSex(userRegisterDTO.getSex());
        newUser.setCreateTime(LocalDateTime.now());

        //插入数据库
        userMapper.insertNewUser(newUser);
        return null;
    }

}

