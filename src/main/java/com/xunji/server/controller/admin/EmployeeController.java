package com.xunji.server.controller.admin;

import com.xunji.common.constant.JwtClaimsConstant;
import com.xunji.common.properties.JwtProperties;
import com.xunji.common.result.Result;
import com.xunji.common.utils.JwtUtil;
import com.xunji.pojo.dto.EmployeeDTO;
import com.xunji.pojo.dto.EmployeeLoginDTO;
import com.xunji.pojo.dto.UserLoginDTO;
import com.xunji.pojo.entity.Employee;
import com.xunji.pojo.entity.User;
import com.xunji.pojo.vo.EmployeeLoginVO;
import com.xunji.pojo.vo.UserLoginVO;
import com.xunji.server.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Api(tags = "员工接口")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    @ApiOperation("教练登录")
    public Result<EmployeeLoginVO> appLogin(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录, 参数: {}", employeeLoginDTO);

        // 调用业务层登录方法
        Employee employee = employeeService.Login(employeeLoginDTO);

        //jwt令牌
        Map<String,Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        log.info("生成JWT时的claims内容: {}", claims); // 添加日志
        String jwt = JwtUtil.createJWT(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .token(jwt)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出登录
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation("教练退出登录")
    public Result<String> logout() {
        log.info("员工退出登录");
        return Result.success();
    }

    /**
     * 新增员工信息
     */
    @GetMapping("/save")
    @ApiOperation("新增员工信息")
    public Result get(@RequestBody EmployeeDTO employeeDTO) {
        log.info("新增员工信息: {}", employeeDTO);
        employeeService.save(employeeDTO);
        return Result.success();
    }
}
