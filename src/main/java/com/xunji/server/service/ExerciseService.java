package com.xunji.server.service;

import com.xunji.pojo.entity.Exercise;
import com.xunji.pojo.vo.ExerciseVO;

import java.util.List;

public interface ExerciseService {
    /**
     * 查询动作
     *
     * @return
     */
    List<ExerciseVO> selectExercise(Exercise exercise);
}
