package com.xunji.server.service;

import com.xunji.common.result.Result;
import com.xunji.pojo.dto.UserLoginDTO;
import com.xunji.pojo.dto.UserRegisterDTO;
import com.xunji.pojo.entity.User;

public interface UserService {

    /**
     * 用户登录
     *
     * @param userLoginDTO 用户登录信息
     * @return 登录结果
     */
    User appLogin(UserLoginDTO userLoginDTO);

    /**
     * 用户注册
     *
     * @param userRegisterDTO 用户注册信息
     * @return 注册结果
     */
    Result register(UserRegisterDTO userRegisterDTO);
}
