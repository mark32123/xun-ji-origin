package com.xunji.server.service.impl;

import com.xunji.pojo.entity.ExerciseCategory;
import com.xunji.server.mapper.ExerciseCategoryMapper;
import com.xunji.server.mapper.ExerciseMapper;
import com.xunji.server.service.ExerciseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseCategoryServiceImpl implements ExerciseCategoryService {

    @Autowired
    private ExerciseCategoryMapper exerciseCategoryMapper;


    @Override
    public List<ExerciseCategory> list(Integer type) {
        return exerciseCategoryMapper.list(type);
    }
}
