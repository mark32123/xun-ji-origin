package com.xunji.server.service;

import com.xunji.pojo.entity.User;
import com.xunji.pojo.vo.UserLogin;
import org.springframework.stereotype.Service;

public interface UserService {

    /**
     * 用户登录
     *
     * @param userLogin 用户登录信息
     * @return 登录结果
     */
    User login(UserLogin userLogin);
}
