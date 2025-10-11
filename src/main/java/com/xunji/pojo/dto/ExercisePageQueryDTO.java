package com.xunji.pojo.dto;

import lombok.Data;

@Data
public class ExercisePageQueryDTO {
    private int page;

    private int pageSize;

    private String name;

    //分类id
    private Integer exerciseId;

}
