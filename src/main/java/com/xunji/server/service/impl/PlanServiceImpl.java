package com.xunji.server.service.impl;

import com.xunji.pojo.dto.PlanDTO;
import com.xunji.pojo.entity.Plan;
import com.xunji.pojo.entity.PlanForExercise;
import com.xunji.pojo.vo.ExerciseItemVO;
import com.xunji.pojo.vo.PlanVO;
import com.xunji.server.mapper.PlanMapper;
import com.xunji.server.service.PlanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {


    @Autowired
    private PlanMapper planMapper;
    /**
     * 条件查询
     * @param plan
     * @return
     */
    public List<Plan> list(Plan plan) {
        List< Plan> list = planMapper.list(plan);
        return list;
    }

    /**
     * 根据计划id查询动作
     * @param id
     * @return
     */
    public List<ExerciseItemVO> exerciseList(Long id) {return planMapper.exerciseList(id);}

    /**
     * 新增训练计划
     */
    @Override
    public void addPlan(PlanDTO planDTO) {
        Plan plan = new Plan();
        BeanUtils.copyProperties(planDTO, plan);

        //插入训练计划
        planMapper.insert(plan);

        List<PlanForExercise> planforExercises = planDTO.getPlanforExercises();
        for (PlanForExercise planforExercise : planforExercises) {
            planforExercise.setPlanId(plan.getId());
        }
        //保存动作与训练计划关系
        planMapper.insertPlanForExercise(planforExercises);
    }

    /**
     *         Plan plan = planMapper.getById(id);
     *         List<PlanForExercise> planForExercises = planMapper.exerciseList(id);
     *
     *         PlanVO planvo = new PlanVO();
     *         BeanUtils.copyProperties(plan, planvo);
     *         planvo.setPlanForExercises(planForExercises);
     *         return planvo;
     */


}
