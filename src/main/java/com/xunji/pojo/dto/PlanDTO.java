package com.xunji.pojo.dto;

import com.xunji.pojo.entity.PlanForExercise;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PlanDTO {

    //计划id
    private Long id;

    //计划名称
    private String name;

    //计划分类id
    private Long categoryId;

    //计划描述
    private String description;

    //计划状态
    private Integer status;

    //计划创建时间
    private LocalDateTime createTime;

    //计划更新时间
    private LocalDateTime updateTime;

    //计划创建人
    private Long createUser;

    //计划更新人
    private Long updateUser;

    //训练计划和动作关系
    private List<PlanForExercise> planforExercises = new ArrayList<>();
}
