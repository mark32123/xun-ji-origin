package com.xunji.server.mapper;

import com.github.pagehelper.Page;
import com.xunji.common.enumeration.OperationType;
import com.xunji.pojo.dto.ExercisePageQueryDTO;
import com.xunji.pojo.entity.Exercise;
import com.xunji.pojo.vo.ExerciseVO;
import com.xunji.server.annotation.AutoFill;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    /**
     * 根据id查询动作
     * @param id
     * @return
     */
    @Select("select * from exercise_action where id = #{id}")
    Exercise getById(Long id);

    /**
     * 删除动作
     * @param id
     */
    @Delete("delete from exercise_action where id = #{id}")
    void delete(Long id);

    /**
     * 修改动作
     * @param exercise
     */
    @Update("update exercise_action set name = #{name},description = #{description},image = #{image},status = #{status} where id = #{id}")
    void update(Exercise exercise);

    /**
     * 根据分类id查询动作
     * @param categoryId
     * @return
     */
    @Select("select * from exercise_action where category_id = #{categoryId}")
    List<Exercise> list(Long categoryId);

    /**
     * 启用或禁用动作
     * @param exercise
     */
    @Update("update exercise_action set status = #{status} where id = #{id}")
    void updateStatus(Exercise exercise);

    /**
     * 根据计划id查询动作
     * @param id
     * @return
     */
    @Select("select * from exercise_action where id in (select exercise_id from plan_for_exercise where plan_id = #{id})")
    List<Exercise> listExercise(Long id);
}
