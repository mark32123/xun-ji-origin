package com.xunji.pojo.vo;

import com.xunji.pojo.entity.PlanForExercise;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanVO {

    private Long id;

    //分类id
    private Long categoryId;

    //计划名称
    private String name;

    //状态 0:停用 1:启用
    private Integer status;

    //描述信息
    private String description;

    //创建时间
    private LocalDateTime createTime;

    //更新时间
    private LocalDateTime updateTime;

    //创建人
    private Long createUser;

    //更新人
    private Long updateUser;

    //训练计划和动作关系
    private List<PlanForExercise> planForExercises = new ArrayList<>();
}
