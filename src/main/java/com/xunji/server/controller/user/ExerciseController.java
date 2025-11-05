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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("userExerciseController")
@RequestMapping("/user/exercise")
@Slf4j
@Api(tags = "C端-训练动作浏览接口")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;


/**
 * 根据动作分类id查询动作
 * @param categoryId 分类ID
 * @return 动作列表
 */
@GetMapping("/list")
@ApiOperation("根据动作分类id查询动作")
public Result<List<ExerciseVO>> list(Long categoryId){
    log.info("根据动作分类id查询动作, 参数: {}", categoryId);
    List<ExerciseVO> exerciseList = exerciseService.selectExercise(categoryId);
    return Result.success(exerciseList);
}

    /**
     * 根据动作id查看动作技术要点
     * @param
     * @return
     */
    @GetMapping("/detail")
    @ApiOperation("根据动作id查看动作技术要点")
    public Result<ExerciseVO> detail(Long id){
        log.info("根据动作id查看动作技术要点, 参数: {}", id);
        ExerciseVO exerciseVO = exerciseService.getInfoById(id);
        return Result.success(exerciseVO);
    }


}
