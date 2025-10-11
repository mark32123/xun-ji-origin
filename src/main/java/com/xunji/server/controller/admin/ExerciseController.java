package com.xunji.server.controller.admin;

import com.xunji.common.result.Result;
import com.xunji.pojo.dto.ExerciseDTO;
import com.xunji.server.service.ExerciseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
