package com.xunji.server.service;

import com.xunji.common.result.PageResult;
import com.xunji.pojo.dto.ExerciseDTO;
import com.xunji.pojo.dto.ExercisePageQueryDTO;
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

    /**
     * 分页查询
     * @param exercisePageQueryDTO
     * @return
     */
    PageResult pageQuery(ExercisePageQueryDTO exercisePageQueryDTO);

    /**
     * 教练删除动作
     * @param ids
     */
    void delete(List<Long> ids);

    /**
     * 教练修改动作
     * @param exerciseDTO
     */
    void update(ExerciseDTO exerciseDTO);
}
