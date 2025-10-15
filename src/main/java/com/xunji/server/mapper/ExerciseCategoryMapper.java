package com.xunji.server.mapper;

import com.xunji.pojo.entity.ExerciseCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExerciseCategoryMapper {

    /**
     * 查询所有分类
     * @param
     * @return
     */
    @Select("SELECT * FROM exercise_category")
    List<ExerciseCategory> list();
}
