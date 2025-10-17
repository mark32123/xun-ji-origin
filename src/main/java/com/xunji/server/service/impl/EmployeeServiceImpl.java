package com.xunji.server.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xunji.common.constant.MessageConstant;
import com.xunji.common.constant.PasswordConstant;
import com.xunji.common.constant.StatusConstant;
import com.xunji.common.result.PageResult;
import com.xunji.pojo.dto.EmployeeDTO;
import com.xunji.pojo.dto.EmployeeLoginDTO;
import com.xunji.pojo.dto.EmployeePageQueryDTO;
import com.xunji.pojo.entity.Employee;
import com.xunji.server.mapper.EmployeeMapper;
import com.xunji.server.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalTime;
import java.util.List;

@Service
@Slf4j
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

    /**
     * 新增员工
     * @param employeeDTO 新增员工信息
     */
    public void save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        employee.setStatus(StatusConstant.ENABLE);

        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));//md5加密
        employeeMapper.insert(employee);
    }

    /**
     * 分页查询
     * @param employeePageQueryDTO 分页查询参数
     * @return
     */
    public PageResult page(EmployeePageQueryDTO employeePageQueryDTO) {
        PageHelper.startPage(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());
        Page<Employee> page = employeeMapper.page(employeePageQueryDTO);

        Long total = page.getTotal();
        List<Employee> records = page.getResult();

        log.info("分页查询结果 - 总数: {}, 记录数: {}", page.getTotal(), page.getResult().size());
        return new PageResult(total, records);
    }

    /**
     * 更新教练数据
     * @param status 状态
     * @param id 员工id
     */
    public void startOrStop(Integer status, Long id) {
            Employee employee = Employee.builder()
                    .status(status)
                    .id(id)
                    .build();
            employeeMapper.startOrStop(employee);
    }

    /**
     *  编辑教练信息
     * @param employeeDTO 编辑教练信息
     */
    public void update(EmployeeDTO employeeDTO) {
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeDTO, employee);

            employee.setUpdateTime(LocalTime.now());
            employee.setUpdateUser(employeeDTO.getId());

            employeeMapper.update(employee);

    }

    /**
     * 根据id查询教练信息
     * @param id 员工id
     * @return
     */
    public Employee getById(Long id) {
        Employee employee = employeeMapper.getById(id);
        if(employee != null){
            employee.setPassword("******");
        }
        return employee;
    }
}
