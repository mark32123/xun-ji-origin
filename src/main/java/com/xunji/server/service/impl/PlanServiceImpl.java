package com.xunji.server.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xunji.common.constant.MessageConstant;
import com.xunji.common.constant.StatusConstant;
import com.xunji.common.result.PageResult;
import com.xunji.pojo.dto.PlanDTO;
import com.xunji.pojo.dto.PlanPageQueryDTO;
import com.xunji.pojo.entity.Exercise;
import com.xunji.pojo.entity.Plan;
import com.xunji.pojo.entity.PlanForExercise;
import com.xunji.pojo.vo.ExerciseItemVO;
import com.xunji.pojo.vo.PlanVO;
import com.xunji.server.mapper.ExerciseMapper;
import com.xunji.server.mapper.PlanForExerciseMapper;
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
    @Autowired
    private PlanForExerciseMapper planforExerciseMapper;
    @Autowired
    private ExerciseMapper exerciseMapper;

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
     * 启动或停止计划
     * @param status
     * @param id
     */
    public void startOrStop(Integer status, Long id) {
        //启用计划时，判断计划内是否有禁用动作
        if(status == StatusConstant.ENABLE){
            //查询计划内所有动作
            List<Exercise> exercises = exerciseMapper.listExercise(id);
            exercises.forEach(exercise -> {
                if(exercise.getStatus() == StatusConstant.DISABLE){
                    throw new RuntimeException(MessageConstant.PLAN_ENABLE_FAILED);
                }
            });
        }

        Plan plan = new Plan();
        plan.builder()
                .id(id)
                .status(status)
                .build();
        planMapper.updateStatus(plan);
    }

    /**
     * 批量删除计划
     * @param ids
     */
    public void deletePlan(List<Long> ids) {
        ids.forEach(id -> {
            Plan plan = planMapper.getById(id);
            if(plan.getStatus() == StatusConstant.ENABLE){
                throw new RuntimeException(MessageConstant.PLAN_DELETE_FAILED);
            }
        });

        //删除计划
        ids.forEach(planId ->{
            //删除计划内所有动作
            planMapper.deleteById(planId);
            //删除计划关联数据
            planforExerciseMapper.deleteByPlanId(planId);
        });
    }

    /**
     * 分页查询
     * @param planPageQueryDTO
     * @return
     */
    public PageResult pageQuery(PlanPageQueryDTO planPageQueryDTO) {
        int pageNum = planPageQueryDTO.getPage();
        int pageSize = planPageQueryDTO.getPageSize();

        PageHelper.startPage(pageNum, pageSize);
        Page<PlanVO> page = planMapper.pageQuery(planPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 根据id查询计划
     * @param id
     * @return
     */
    public PlanVO getPlanWithId(Long id) {
        Plan plan = planMapper.getById(id);
        List<PlanForExercise> planForExercises = planMapper.getByplanId(id);

        PlanVO planvo = new PlanVO();
        BeanUtils.copyProperties(plan, planvo);
        planvo.setPlanForExercises(planForExercises);
        return planvo;
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
