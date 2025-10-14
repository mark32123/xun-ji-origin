package com.xunji.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeDTO implements Serializable {
    //员工id
    private Long id;

    // 姓名
    private String name;

    //用户名
    private String username;

    // 手机
    private String phone;

    // 密码
    private String password;

    //性别
    private String sex;
}
