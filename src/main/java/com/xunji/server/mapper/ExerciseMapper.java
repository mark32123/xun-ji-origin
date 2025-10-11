package com.xunji.server.mapper;

import com.xunji.pojo.entity.Exercise;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExerciseMapper {

    /**
     * 查询动作
     * @return
     */

    List<Exercise> selectExercise(Exercise exercise);
}
