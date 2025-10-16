package com.xunji.server.mapper;

import com.github.pagehelper.Page;
import com.xunji.pojo.dto.EmployeePageQueryDTO;
import com.xunji.pojo.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


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

    /**
     * 分页查询
     * @param employeePageQueryDTO
     * @return
     */
    @Select("select * from admin limit #{page}, #{pageSize}")
    Page<Employee> page(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 修改员工信息
     * @param employee
     */
    @Update("update admin set username = #{username}, name = #{name}, phone = #{phone}, sex = #{sex}, status = #{status} where id = #{id}")
    void update(Employee employee);

    /**
     * 启用禁用员工账号
     * @param employee
     */
    @Update("update admin set status = #{status} where id = #{id}")
    void startOrStop(Employee employee);

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    @Select("select * from admin where id = #{id}")
    Employee getById(Long id);
}
