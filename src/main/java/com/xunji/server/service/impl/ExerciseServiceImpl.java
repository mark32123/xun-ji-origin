package com.xunji.server.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xunji.common.constant.StatusConstant;
import com.xunji.common.result.PageResult;
import com.xunji.pojo.dto.ExerciseDTO;
import com.xunji.pojo.dto.ExercisePageQueryDTO;
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
    @Autowired
    private ExerciseService exerciseService;

    /**
     * 用户查询动作
     *
     * @return
     */
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

    /**
     * 教练动作分页查询
     *
     * @param exercisePageQueryDTO
     * @return
     */
    public PageResult pageQuery(ExercisePageQueryDTO exercisePageQueryDTO) {
        PageHelper.startPage(exercisePageQueryDTO.getPage(), exercisePageQueryDTO.getPageSize());
        Page<ExerciseVO> page = exerciseMapper.pageQuery(exercisePageQueryDTO);
        PageResult pageResult = new PageResult(page.getTotal(), page);
        return pageResult;
    }

    /**
     * 教练删除动作
     * @param ids
     */
    //TODO: 删除动作中关联训练计划功能
    @Transactional
    public void delete(List<Long> ids) {

        //判断当前动作是否开启，开启则不能删除
        ids.forEach(id -> {
            Exercise exercise = exerciseMapper.getById(id);
            if (exercise.getStatus() == StatusConstant.ENABLE) {
                throw new RuntimeException("当前动作已开启，不能删除");
            }
        });

        //判断当前动作是否被计划使用，被计划使用则不能删除


        //删除动作
        ids.forEach(id -> exerciseMapper.delete(id));

    }

    /**
     * 教练修改动作
     * @param exerciseDTO
     */
    public void update(ExerciseDTO exerciseDTO) {

        Exercise exercise = new Exercise();
        BeanUtils.copyProperties(exerciseDTO, exercise);
        exerciseMapper.update(exercise);
    }

    /**
     * 教练根据id查询动作
     * @param id
     * @return
     */
    public ExerciseVO getById(Long id) {
        Exercise exercise = exerciseMapper.getById(id);

        ExerciseVO exerciseVO = new ExerciseVO();
        BeanUtils.copyProperties(exercise, exerciseVO);
        return exerciseVO;
    }

    /**
     * 根据动作分类id查询动作
     * @param categoryId
     * @return
     */
    public List<Exercise> list(Long categoryId) {
        Exercise exercise = Exercise.builder()
                .categoryId(categoryId)
                .status(StatusConstant.ENABLE)
                .build();
        return exerciseMapper.list(categoryId);

    }
}
