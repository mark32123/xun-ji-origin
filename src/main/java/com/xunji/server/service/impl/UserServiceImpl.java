package com.xunji.server.service.impl;

import com.xunji.pojo.entity.User;
import com.xunji.pojo.vo.UserLogin;
import com.xunji.server.mapper.UserMapper;
import com.xunji.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    /**
     * 用户登录
     *
     */

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(UserLogin userLogin) {
        User user = userMapper.findByName(userLogin.getName());

        // 验证密码（实际项目中应该使用加密算法）
        if (user != null && user.getPassword().equals(userLogin.getPassword())) {
            return user;
        }

        return null;
    }
}
