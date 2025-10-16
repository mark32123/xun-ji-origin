package com.xunji.server.service.impl;

import com.xunji.common.constant.MessageConstant;
import com.xunji.pojo.dto.EmployeeLoginDTO;
import com.xunji.pojo.entity.Employee;
import com.xunji.server.mapper.EmployeeMapper;
import com.xunji.server.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 员工登录
     * @param employeeLoginDTO 员工登录信息
     * @return
     */
    public Employee Login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        Employee employee = employeeMapper.getByUsername(username);
        // 用户名不存在
        if(employee == null){
            throw new RuntimeException(MessageConstant.USER_NOT_FOUND);
        }

        // 密码错误
        if(!employee.getPassword().equals(password)){
            throw new RuntimeException(MessageConstant.PASSWORD_ERROR);
        }
        return employee;
    }
}
