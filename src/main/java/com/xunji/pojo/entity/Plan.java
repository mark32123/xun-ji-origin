package com.xunji.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 训练计划
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Plan {
    //主键
    private Long id;

    //分类id
    private Long categoryId;

    //计划名称
    private String name;

    //状态 0:停用 1:启用
    private Integer status;

    //描述信息
    private String description;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long createUser;

    private Long updateUser;
}
