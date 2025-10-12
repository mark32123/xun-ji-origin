package com.xunji.server.controller.user;

import com.xunji.common.constant.StatusConstant;
import com.xunji.common.result.Result;
import com.xunji.pojo.entity.Plan;
import com.xunji.pojo.vo.ExerciseItemVO;
import com.xunji.server.service.PlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cache.annotation.Cacheable;


import java.util.List;

@RestController("userPlanController")
@RequestMapping("/user/plan")
@Api("C端-训练计划接口")
public class PlanController {

    @Autowired
    private PlanService planService;

    /**
     * 条件查询
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("根据分类id查询训练计划")
    @Cacheable(value = "planCache", key = "#categoryId")
    public Result<List<Plan>> list(Long categoryId) {

        Plan plan = new Plan();
        plan.setCategoryId(categoryId);
        plan.setStatus(StatusConstant.ENABLE);

        List<Plan> list = planService.list(plan);
        return Result.success(list);
    }


    /**
     * 根据训练计划id查询动作
     */
    @GetMapping("/exercise/{id}")
    @ApiOperation("根据训练计划id查询动作")
    public Result<List<ExerciseItemVO>> exerciseList(@PathVariable("id") Long id){

        List<ExerciseItemVO> list = planService.exerciseList(id);
        return Result.success(list);
    }

}
