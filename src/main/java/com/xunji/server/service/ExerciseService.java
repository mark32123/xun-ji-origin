package com.xunji.server.service;

import com.xunji.pojo.dto.ExerciseDTO;
import com.xunji.pojo.entity.Exercise;
import com.xunji.pojo.vo.ExerciseVO;

import java.util.List;

public interface ExerciseService {
    /**
     * 用户查询动作
     *
     * @return
     */
    List<ExerciseVO> selectExercise(Exercise exercise);

    /**
     * 教练添加动作
     *
     * @param exerciseDTO
     */
    void add(ExerciseDTO exerciseDTO);
}
