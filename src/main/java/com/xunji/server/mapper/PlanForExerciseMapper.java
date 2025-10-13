package com.xunji.server.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlanForExerciseMapper {

    /**
     * 批量删除计划动作关系
     * @param planId
     */
    @Delete("DELETE FROM plan_for_exercise WHERE plan_id = #{planId}")
    void deleteByPlanId(Long planId);
}
