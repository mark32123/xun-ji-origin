package com.xunji.pojo.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录信息 DTO 类
 */
@Data
public class UserLoginDTO implements Serializable{
    private String phone;    // 手机号
    private String password; // 密码
}
