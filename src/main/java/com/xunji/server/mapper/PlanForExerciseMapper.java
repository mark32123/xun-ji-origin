package com.xunji.server.mapper;

import com.xunji.pojo.entity.Exercise;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PlanForExerciseMapper {

    /**
     * 批量删除计划动作关系
     * @param planId
     */
    @Delete("DELETE FROM plan_for_exercise WHERE plan_id = #{planId}")
    void deleteByPlanId(Long planId);

    /**
     * 根据动作id查询计划id
     */
    @Select("SELECT plan_id FROM plan_for_exercise WHERE exercise_id IN (#{exerciseIds})")
    List<Long> getPlanIdByExerciseId(List<Long> exerciseIds);
}
