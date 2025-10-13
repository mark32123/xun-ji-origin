package com.xunji.server.controller.admin;

import com.xunji.common.result.Result;
import com.xunji.pojo.dto.PlanDTO;
import com.xunji.server.service.PlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminPlanController")
@RequestMapping("/admin/plan")
@Api("A端-训练计划接口")
public class PlanController {

    @Autowired
    private PlanService planService;


    /**
     * 新增训练计划
     */
    @RequestMapping("/add")
    @ApiOperation("新增训练计划")
    @Cacheable(value = "planCache", key = "#planDTO.categoryId")
    public Result addPlan(@RequestBody PlanDTO planDTO){

        planService.addPlan(planDTO);
        return Result.success();
    }

    /**
     * 批量删除训练计划
     */
    @RequestMapping("/delete")
    @ApiOperation("删除训练计划")
    public Result deletePlan(@RequestParam List<Long> ids){
        planService.deletePlan(ids);
        return Result.success();
    }

    /**
     * 训练计划启用与禁用
     */
    @RequestMapping("/startOrStop")
    @ApiOperation("训练计划启用与禁用")
    @CacheEvict(value = "planCache", allEntries = true)
    public Result startOrStop(@PathVariable Integer status, Long id){
        planService.startOrStop(status,id);
        return Result.success();
    }

}
