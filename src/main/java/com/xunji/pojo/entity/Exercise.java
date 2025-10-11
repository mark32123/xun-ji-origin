package com.xunji.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 动作
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Exercise {
    private Long id;

    //动作名称
    private String name;

    //动作分类id
    private Long exerciseId;

    //图片
    private String image;

    //描述信息
    private String description;

    //0 禁用 1 开启
    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long createUser;

    private Long updateUser;
}
