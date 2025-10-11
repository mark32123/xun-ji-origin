package com.xunji.server.controller.admin;

import com.xunji.common.result.PageResult;
import com.xunji.common.result.Result;
import com.xunji.pojo.dto.ExerciseDTO;
import com.xunji.pojo.dto.ExercisePageQueryDTO;
import com.xunji.pojo.entity.Exercise;
import com.xunji.pojo.vo.ExerciseVO;
import com.xunji.server.service.ExerciseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 训练动作管理
 */
@RestController("adminExerciseController")
@RequestMapping("/admin/exercise")
@Api(tags = "A端-训练动作管理")
@Slf4j
public class ExerciseController {


    @Autowired
    private ExerciseService exerciseService;

    /**
     * 添加训练动作
     */
    @PostMapping("/add")
    @ApiOperation("添加训练动作")
    public Result add(@RequestBody ExerciseDTO exerciseDTO){
        log.info("添加训练动作,参数:{}",exerciseDTO);

        exerciseService.add(exerciseDTO);
        return Result.success();

    }

    /**
     * 动作分页查询
     */
    @GetMapping("/page")
    @ApiOperation("动作分页查询")
    public Result page(ExercisePageQueryDTO exercisePageQueryDTO){
        log.info("动作分页查询,参数:{}",exercisePageQueryDTO);
        PageResult pageResult = exerciseService.pageQuery(exercisePageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 删除动作
     */
    @DeleteMapping("/delete")
    @ApiOperation("批量删除动作")
    public Result delete(@RequestParam List<Long> id){
        log.info("删除动作,参数:{}",id);
        exerciseService.delete(id);
        return Result.success();
    }

    /**
     * 修改动作
     */

    @PutMapping("/update")
    @ApiOperation("修改动作")
    public Result update(@RequestBody ExerciseDTO exerciseDTO){
        log.info("修改动作,参数:{}",exerciseDTO);
        exerciseService.update(exerciseDTO);
        return Result.success();
    }

    /**
     * 根据id查询动作
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询动作")
    public Result<ExerciseVO> getById(@RequestBody Long id){
        log.info("根据id查询动作,参数:{}",id);
        ExerciseVO exerciseVO = exerciseService.getById(id);
        return Result.success(exerciseVO);
    }

    /**
     * 根据动作分类id查询动作
     */
    @GetMapping("/list")
    @ApiOperation("根据动作分类id查询动作")
    public Result<List<Exercise>> list(Long categoryId){
        log.info("根据动作分类id查询动作,参数:{}",categoryId);
        List<Exercise> list = exerciseService.list(categoryId);
        return Result.success(list);
    }

}
