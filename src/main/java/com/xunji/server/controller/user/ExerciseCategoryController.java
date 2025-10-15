package com.xunji.server.controller.user;

import com.xunji.common.result.Result;
import com.xunji.pojo.entity.ExerciseCategory;
import com.xunji.server.service.ExerciseCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userExerciseCategoryController")
@RequestMapping("/user/exerciseCategory")
@Api(tags = "C端-训练动作分类接口")
@Slf4j
public class ExerciseCategoryController {

    @Autowired
    private ExerciseCategoryService exerciseCategoryService;


    /**
     * 查询所有分类
     *
     * @param
     * @return
     */
    @RequestMapping("/list")
    @ApiOperation("查询所有分类")
    public Result<List<ExerciseCategory>> list() {
        log.info("查询所有分类");
        List<ExerciseCategory> list = exerciseCategoryService.list();
        log.info("Controller返回的数据: {}", list);
        return Result.success(list);
    }
}
