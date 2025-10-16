package com.xunji.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanForExercise {

    private Long id;

    //训练计划id
    private Long planId;

    //动作id
    private Long exerciseId;

    //动作名称 （冗余字段）
    private String name;

    //组数
    private Integer setCount;

    //每组重量
    private BigDecimal weight;

    //每组次数
    private Integer count;

}
