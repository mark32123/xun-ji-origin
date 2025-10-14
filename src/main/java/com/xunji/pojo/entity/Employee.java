package com.xunji.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
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

    //状态
    private Integer status;

    //创建时间
    private LocalTime createTime;

    //更新时间
    private LocalTime updateTime;

    //创建人
    private Long createUser;

    //修改人
    private Long updateUser;
}
