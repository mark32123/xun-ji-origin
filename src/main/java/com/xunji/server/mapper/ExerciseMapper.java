package com.xunji.server.mapper;

import com.github.pagehelper.Page;
import com.xunji.common.enumeration.OperationType;
import com.xunji.pojo.dto.ExercisePageQueryDTO;
import com.xunji.pojo.entity.Exercise;
import com.xunji.pojo.vo.ExerciseVO;
import com.xunji.server.annotation.AutoFill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExerciseMapper {

    /**
     * 用户查询动作
     * @return
     */
    List<Exercise> selectExercise(Exercise exercise);

    /**
     * 教练添加动作
     * @param exercise
     */
    @AutoFill(value = OperationType.INSERT)
    void add(Exercise exercise);

    /**
     * 教练动作分页查询
     * @param exercisePageQueryDTO
     * @return
     */
    Page<ExerciseVO> pageQuery(ExercisePageQueryDTO exercisePageQueryDTO);
}
