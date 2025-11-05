package com.xunji.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseVO {
    private Long id;
    //动作名称
    private String name;
    //图片
    private String image;
    //详细描述
    private String description;

    private String techniquePoints; // 技术要点
    private String precautions;    // 注意事项
}
