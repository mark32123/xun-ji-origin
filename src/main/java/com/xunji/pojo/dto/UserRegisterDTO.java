package com.xunji.pojo.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户注册信息 DTO 类
 */
@Data
public class UserRegisterDTO {

    private String phone;
    private String password;
    private String name;
    private String sex;
    private LocalDateTime createTime;
}
