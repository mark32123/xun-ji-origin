package com.xunji.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ExerciseCategoryDTO implements Serializable {
    //主键
    private Long id;

    //类型 1 动作分类 2 训练分类
    private Integer type;

    //分类名称
    private String name;

    //排序
    private Integer sort;
}
