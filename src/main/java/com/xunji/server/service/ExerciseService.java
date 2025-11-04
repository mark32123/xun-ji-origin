package com.xunji.server.service;

import com.xunji.common.result.PageResult;
import com.xunji.pojo.dto.ExerciseDTO;
import com.xunji.pojo.dto.ExercisePageQueryDTO;
import com.xunji.pojo.entity.Exercise;
import com.xunji.pojo.vo.ExerciseVO;

import java.util.List;

public interface ExerciseService {
    /**
     * 用户根据分类id查询动作
     *
     * @return
     */
    List<ExerciseVO> selectExercise(Long categoryId);

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

    /**
     * 教练根据id查询动作
     * @param id
     * @return
     */
    ExerciseVO getById(Long id);

    /**
     * 教练根据分类id查询动作
     * @param categoryId
     * @return
     */
    List<Exercise> list(Long categoryId);

    /**
     * 启动或禁用动作
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);
}
