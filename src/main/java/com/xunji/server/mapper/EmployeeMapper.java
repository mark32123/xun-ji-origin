package com.xunji.server.mapper;

import com.github.pagehelper.Page;
import com.xunji.pojo.dto.EmployeePageQueryDTO;
import com.xunji.pojo.entity.Employee;
import org.apache.ibatis.annotations.*;


@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     *
     * @param username
     * @return
     */
    @Select("select * from admin where username = #{username}")
    Employee getByUsername(String username);

    /**
     * 新增员工
     *
     * @param employee
     */
    @Insert("insert into admin (username, name, phone, sex, password) values (#{username}, #{name}, #{phone}, #{sex}, #{password})")
    void insert(Employee employee);

    /**
     * 分页查询
     *
     * @param employeePageQueryDTO
     * @return
     */
    @Results(id = "employee", value = {
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "create_user", property = "createUser"),
            @Result(column = "update_user", property = "updateUser")
    })
    @Select("select * from admin ")
    Page<Employee> page(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 修改员工信息
     *
     * @param employee
     */
    @Update("update admin set username = #{username}, name = #{name}, phone = #{phone}, sex = #{sex}, status = #{status} where id = #{id}")
    void update(Employee employee);

    /**
     * 启用禁用员工账号
     *
     * @param employee
     */
    @Update("update admin set status = #{status} where id = #{id}")
    void startOrStop(Employee employee);

    /**
     * 根据id查询员工
     *
     * @param id
     * @return
     */
    @Results({
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "create_user", property = "createUser"),
            @Result(column = "update_user", property = "updateUser")
    })
    @Select("SELECT id, name, phone, sex, status, username, create_time, create_user, update_time, update_user FROM admin WHERE id = #{id}")
    Employee getById(Long id);

}
