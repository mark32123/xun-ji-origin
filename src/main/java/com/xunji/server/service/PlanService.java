package com.xunji.server.service;

import com.xunji.common.result.PageResult;
import com.xunji.pojo.dto.PlanDTO;
import com.xunji.pojo.dto.PlanPageQueryDTO;
import com.xunji.pojo.entity.Plan;
import com.xunji.pojo.vo.ExerciseItemVO;
import com.xunji.pojo.vo.PlanVO;

import java.util.List;

public interface PlanService {
    /**
     * 条件查询
     * @param plan
     * @return
     */
    List<Plan> list(Plan plan);

    /**
     * 根据训练计划id查询动作
     */
    List<ExerciseItemVO> exerciseList(Long id);

    /**
     * 新增训练计划
     */
    void addPlan(PlanDTO planDTO);

    /**
     * 启动或停止计划
     */
    void startOrStop(Integer status, Long id);

    /**
     * 删除计划
     */
    void deletePlan(List<Long> ids);

    /**
     * 训练计划分页查询
     */
    PageResult pageQuery(PlanPageQueryDTO planPageQueryDTO);

    /**
     * 根据计划id查询计划
     */
    PlanVO getPlanWithId(Long id);
}
