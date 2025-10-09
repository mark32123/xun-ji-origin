package com.xunji.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    // 用户名
    private String name;

    // 密码
    private String password;

    // 手机号
    private String phone;

    // 用户id
    private Long id;

    // 性别
    private String sex;

    //头像
    private String avatar;

    //注册时间
    private LocalDateTime createTime;
}
