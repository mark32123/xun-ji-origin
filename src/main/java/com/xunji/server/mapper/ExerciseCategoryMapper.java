package com.xunji.server.mapper;

import com.xunji.pojo.entity.ExerciseCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExerciseCategoryMapper {

    /**
     * 查询所有分类
     * @param
     * @return
     */
    @Select("SELECT * FROM exercise_category")
    @Results({
            @Result(column = "created_time", property = "createTime"),
            @Result(column = "updated_time", property = "updateTime"),
            @Result(column = "create_user", property = "createUser"),
            @Result(column = "update_user", property = "updateUser")
    })
    List<ExerciseCategory> list();
}
