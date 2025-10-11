package com.xunji.server.service.impl;

import com.xunji.pojo.dto.ExerciseDTO;
import com.xunji.pojo.entity.Exercise;
import com.xunji.pojo.vo.ExerciseVO;
import com.xunji.server.mapper.ExerciseMapper;
import com.xunji.server.service.ExerciseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {


    @Autowired
    private ExerciseMapper exerciseMapper;

    /**
     * 用户查询动作
     *
     * @return
     */
    @Override
    public List<ExerciseVO> selectExercise(Exercise exercise) {
        //查询数据库数据到这个集合中
        List<Exercise> exerciseList = exerciseMapper.selectExercise(exercise);

        //创建一个VO集合传给前端
        List<ExerciseVO> exerciseVOList = new ArrayList<>();

        for (Exercise e : exerciseList) {
            ExerciseVO exerciseVO = new ExerciseVO();
            BeanUtils.copyProperties(e, exerciseVO);        //将数据库查到的e复制给exerciseVO传给前端

            exerciseVOList.add(exerciseVO);
        }

        return exerciseVOList;
    }

    /**
     * 教练添加动作
     *
     * @param exerciseDTO
     */
    @Transactional
    public void add(ExerciseDTO exerciseDTO) {
        Exercise exercise = new Exercise();
        BeanUtils.copyProperties(exerciseDTO, exercise);
        exerciseMapper.add(exercise);
    }
}
