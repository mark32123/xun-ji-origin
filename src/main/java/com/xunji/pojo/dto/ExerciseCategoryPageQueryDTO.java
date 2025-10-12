package com.xunji.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ExerciseCategoryPageQueryDTO implements Serializable {
    //页码
    private int page;

    //每页记录数
    private int pageSize;

    //分类名称
    private String name;

    //分类类型 1动作分类  2训练计划分类
    private Integer type;
}
