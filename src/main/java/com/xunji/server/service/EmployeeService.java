package com.xunji.server.service;

import com.xunji.pojo.dto.EmployeeLoginDTO;
import com.xunji.pojo.entity.Employee;

public interface EmployeeService {
    /**
     * 员工登录
     *
     * @param employeeLoginDTO 员工登录信息
     * @return 登录结果
     */
    Employee Login(EmployeeLoginDTO employeeLoginDTO);
}
