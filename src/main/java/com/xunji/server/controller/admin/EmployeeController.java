package com.xunji.server.controller.admin;

import com.xunji.common.constant.JwtClaimsConstant;
import com.xunji.common.properties.JwtProperties;
import com.xunji.common.result.PageResult;
import com.xunji.common.result.Result;
import com.xunji.common.utils.JwtUtil;
import com.xunji.pojo.dto.EmployeeDTO;
import com.xunji.pojo.dto.EmployeeLoginDTO;
import com.xunji.pojo.dto.EmployeePageQueryDTO;
import com.xunji.pojo.entity.Employee;
import com.xunji.pojo.vo.EmployeeLoginVO;
import com.xunji.server.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 教练管理
 */
@RestController
@RequestMapping("/admin/employee")
@Api(tags = "教练接口")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    @ApiOperation("教练登录")
    public Result<EmployeeLoginVO> appLogin(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("教练登录, 参数: {}", employeeLoginDTO);

        // 调用业务层登录方法
        Employee employee = employeeService.Login(employeeLoginDTO);

        //jwt令牌
        Map<String,Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String jwt = JwtUtil.createJWT(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .token(jwt)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出登录
     */
    @PostMapping("/logout")
    @ApiOperation("教练退出登录")
    public Result<String> logout() {
        log.info("员工退出登录");
        return Result.success();
    }

    /**
     * 新增教练信息
     */
    @GetMapping("/save")
    @ApiOperation("新增员工信息")
    public Result get(@RequestBody EmployeeDTO employeeDTO) {
        log.info("新增员工信息: {}", employeeDTO);
        employeeService.save(employeeDTO);
        return Result.success();
    }

    /**
     * 分页查询教练信息
     */
    @GetMapping("/page")
    @ApiOperation("分页查询教练信息")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("分页查询教练信息,{}", employeePageQueryDTO);
        PageResult page = employeeService.page(employeePageQueryDTO);
        return Result.success(page);
    }


    /**
     * 启用禁用教练账号
     */
    @PostMapping("/status/{status}")
    @ApiOperation("启用禁用教练账号")
    public Result startOrStop(@PathVariable Integer status, Long id) {
        log.info("启用禁用教练账号,参数:{}", id);
        employeeService.startOrStop(status, id);
        return Result.success();
    }

    /**
     * 编辑教练信息
     */
    @PutMapping("/update")
    @ApiOperation("修改教练信息")
    public Result update(@RequestBody EmployeeDTO employeeDTO) {
        log.info("修改教练信息: {}", employeeDTO);
        employeeService.update(employeeDTO);
        return Result.success();
    }

    /**
     * 根据id查询教练信息
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询教练信息")
    public Result<Employee> getById(@PathVariable Long id) {
        log.info("根据id查询教练信息: {}", id);
        Employee employee = employeeService.getById(id);
        return Result.success(employee);
    }

}
