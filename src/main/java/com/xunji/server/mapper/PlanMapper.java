package com.xunji.server.mapper;

import com.xunji.pojo.entity.Exercise;
import com.xunji.pojo.entity.Plan;
import com.xunji.pojo.entity.PlanForExercise;
import com.xunji.pojo.vo.ExerciseItemVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface PlanMapper {

    /**
     * 条件查询
     * @param plan
     * @return
     */
    List<Plan> list(Plan plan);

    /**
     * 用户根据计划id查询动作
     */
    @Select("SELECT id, exercise_id, name, set_count, weight FROM plan_for_exercise WHERE plan_id = #{id}")
    List<ExerciseItemVO> exerciseList(Long id);

    /**
     * 新增训练计划
     */
    @Select("INSERT INTO plan (name, description, status, create_time, update_time, create_user, update_user, category_id) VALUES (#{name}, #{description}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser}, #{categoryId})")
    void insert(Plan plan);

    /**
     * 新增训练计划和动作关系
     */
    @Select("INSERT INTO plan_for_exercise (plan_id, exercise_id, name, set_count, weight) VALUES (#{planId}, #{exerciseId}, #{name}, #{setCount}, #{weight})")
    void insertPlanForExercise(List<PlanForExercise> planforExercises);

    /**
     * 启动或禁用计划
     */
    @Update("UPDATE plan SET status = #{status} WHERE id = #{id}")
    void updateStatus(Plan plan);

    /**
     * 教练根据计划id查询计划
     */
    @Select("SELECT id, name, description, status, create_time, update_time, create_user, update_user, category_id FROM plan WHERE id = #{id}")
    Plan getById(Long id);

    /**
     * 删除计划
     * @param planId
     */
    @Delete("DELETE FROM plan WHERE id = #{planId}")
    void deleteById(Long planId);
}
