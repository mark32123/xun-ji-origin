package com.xunji.server.controller.admin;

import com.xunji.common.result.Result;
import com.xunji.pojo.dto.PlanDTO;
import com.xunji.server.service.PlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
