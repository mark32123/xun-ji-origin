package com.xunji.server.service;

import com.xunji.pojo.dto.UserLoginDTO;
import com.xunji.pojo.entity.User;

public interface UserService {

    /**
     * 用户登录
     *
     * @param userLoginDTO 用户登录信息
     * @return 登录结果
     */
    User appLogin(UserLoginDTO userLoginDTO);
}
