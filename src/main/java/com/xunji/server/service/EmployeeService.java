package com.xunji.server.service;

import com.xunji.common.result.PageResult;
import com.xunji.pojo.dto.EmployeeDTO;
import com.xunji.pojo.dto.EmployeeLoginDTO;
import com.xunji.pojo.dto.EmployeePageQueryDTO;
import com.xunji.pojo.entity.Employee;

public interface EmployeeService {
    /**
     * 教练登录
     *
     * @param employeeLoginDTO 教练登录信息
     * @return 登录结果
     */
    Employee Login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     *
     * @param employeeDTO 新增教练信息
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * 分页查询教练信息
     *
     * @param employeePageQueryDTO 分页查询参数
     * @return 分页查询结果
     */
    PageResult page(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 启用禁用教练账号
     *
     * @param status 状态
     * @param id 员工id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 编辑教练信息
     *
     * @param employeeDTO 编辑教练信息
     */
    void update(EmployeeDTO employeeDTO);

    /**
     * 根据id查询员工信息
     *
     * @param id 员工id
     * @return 员工信息
     */
    Employee getById(Long id);
}
