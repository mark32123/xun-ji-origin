package com.xunji.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDTO implements Serializable {
    private Long id;
    //动作名称
    private String name;
    //动作分类id
    private Long categoryId;
    //图片
    private String image;
    //描述信息
    private String description;
}
