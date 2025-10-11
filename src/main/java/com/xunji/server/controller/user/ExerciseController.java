package com.xunji.server.controller.user;

import com.xunji.common.result.Result;
import com.xunji.pojo.dto.ExerciseDTO;
import com.xunji.pojo.entity.Exercise;
import com.xunji.pojo.vo.ExerciseVO;
import com.xunji.server.service.ExerciseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("userExerciseController")
@RequestMapping("/api/user/exercise")
@Slf4j
@Api(tags = "C端-训练动作浏览接口")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;


    /**
     * 根据动作分类id动作
     * @return
     * 后续可添加redis缓存，待优化
     */
    @GetMapping("/list")
    @ApiOperation("根据动作分类id动作")
    public Result<List<ExerciseVO>> list(Long exerciseId){

        List<ExerciseVO> list = new ArrayList<>();
        //查询数据库
        Exercise exercise = new Exercise();
        exercise.setExerciseId(exerciseId);
        list = exerciseService.selectExercise(exercise);

        return Result.success(list);
    }

}
