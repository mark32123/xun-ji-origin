package com.xunji.server.mapper;

import com.xunji.pojo.entity.Plan;
import com.xunji.pojo.vo.ExerciseItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
     * 根据计划id查询动作
     */
    @Select("SELECT id, exercise_id, name, set_count, weight FROM plan_for_exercise WHERE plan_id = #{id}")
    List<ExerciseItemVO> exerciseList(Long id);
}
