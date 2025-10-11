package com.xunji.server.controller.user;

import com.xunji.common.result.Result;
import com.xunji.pojo.entity.ExerciseCategory;
import com.xunji.server.service.ExerciseCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userExerciseCategoryController")
@RequestMapping("/user/exerciseCategory")
@Api(tags = "C端-训练动作分类接口")
public class ExerciseCategoryController {

    @Autowired
    private ExerciseCategoryService exerciseCategoryService;


    /**
     * 查询所有分类
     * @param type
     * @return
     * 类型: 1动作分类 2训练计划分类
     */
    @RequestMapping("/list")
    @ApiOperation("查询所有分类")
    public Result<List<ExerciseCategory>> list(Integer  type){
            List<ExerciseCategory> list = exerciseCategoryService.list(type);
            return Result.success(list);
    }
}
