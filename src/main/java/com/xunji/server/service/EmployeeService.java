package com.xunji.server.service;

import com.xunji.common.result.PageResult;
import com.xunji.pojo.dto.EmployeeDTO;
import com.xunji.pojo.dto.EmployeeLoginDTO;
import com.xunji.pojo.dto.EmployeePageQueryDTO;
import com.xunji.pojo.entity.Employee;

public interface EmployeeService {
    /**
     * 员工登录
     *
     * @param employeeLoginDTO 员工登录信息
     * @return 登录结果
     */
    Employee Login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     *
     * @param employeeDTO 新增员工信息
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * 分页查询员工信息
     *
     * @param employeePageQueryDTO 分页查询参数
     * @return 分页查询结果
     */
    PageResult page(EmployeePageQueryDTO employeePageQueryDTO);

}
