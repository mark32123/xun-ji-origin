package com.xunji.server.service;

import com.xunji.pojo.entity.ExerciseCategory;

import java.util.List;

public interface ExerciseCategoryService {
    /**
     * 查询所有分类
     * @param type
     * @return
     */
    List<ExerciseCategory> list(Integer type);
}
