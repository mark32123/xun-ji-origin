package com.xunji.server.service.impl;

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
     *         Plan plan = planMapper.getById(id);
     *         List<PlanForExercise> planForExercises = planMapper.exerciseList(id);
     *
     *         PlanVO planvo = new PlanVO();
     *         BeanUtils.copyProperties(plan, planvo);
     *         planvo.setPlanForExercises(planForExercises);
     *         return planvo;
     */
}
