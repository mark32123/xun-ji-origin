package com.xunji.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 用于向用户展示信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseItemVO {

    //动作名称
    private String name;

    //描述信息
    private String description;

    // 图片
    private String image;

    //组数
    private Integer setCount;

    //每组重量
    private BigDecimal weight;

    //每组次数
    private Integer count;
}
