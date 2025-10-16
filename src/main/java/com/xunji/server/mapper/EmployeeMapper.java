package com.xunji.server.mapper;

import com.xunji.pojo.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from admin where username = #{username}")
    Employee getByUsername(String username);

    /**
     * 新增员工
     * @param employee
     */
    @Insert("insert into admin (username, name, phone, sex, password) values (#{username}, #{name}, #{phone}, #{sex}, #{password})")
    void insert(Employee employee);
}
