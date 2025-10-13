package com.xunji.server.controller.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xunji.common.result.PageResult;
import com.xunji.common.result.Result;
import com.xunji.pojo.dto.PlanDTO;
import com.xunji.pojo.dto.PlanPageQueryDTO;
import com.xunji.pojo.vo.PlanVO;
import com.xunji.server.mapper.PlanMapper;
import com.xunji.server.service.PlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
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
    @Autowired
    private PlanMapper planMapper;


    /**
     * 新增训练计划
     */
    @PostMapping("/add")
    @ApiOperation("新增训练计划")
    @Cacheable(value = "planCache", key = "#planDTO.categoryId")
    public Result addPlan(@RequestBody PlanDTO planDTO){

        planService.addPlan(planDTO);
        return Result.success();
    }

    /**
     * 批量删除训练计划
     */
    @DeleteMapping("/delete")
    @ApiOperation("删除训练计划")
    public Result deletePlan(@RequestParam List<Long> ids){
        planService.deletePlan(ids);
        return Result.success();
    }

    /**
     * 训练计划启用与禁用
     */
    @PostMapping("/startOrStop")
    @ApiOperation("训练计划启用与禁用")
    @CacheEvict(value = "planCache", allEntries = true)
    public Result startOrStop(@PathVariable Integer status, Long id){
        planService.startOrStop(status,id);
        return Result.success();
    }

    /**
     *分页查询训练计划
     */
    @GetMapping("/page")
    @ApiOperation("分页查询训练计划")
    public Result<PageResult> pageQuery(PlanPageQueryDTO planPageQueryDTO){
        PageResult pageResult = planService.pageQuery(planPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 根据id查询训练计划
     */
    @GetMapping("/getById")
    @ApiOperation("根据id查询训练计划")
    @Cacheable(value = "planCache", key = "#id")
    public Result<PlanVO> getById(@PathVariable Long id){
        PlanVO planVO = planService.getPlanWithId(id);
        return Result.success(planVO);
    }

    /**
     * 修改训练计划
     */
    @PutMapping("/update")
    @ApiOperation("修改训练计划")
    @CacheEvict(value = "planCache", allEntries = true)
    public Result update(@RequestBody PlanDTO planDTO){
        planService.update(planDTO);
        return Result.success();
    }


}
