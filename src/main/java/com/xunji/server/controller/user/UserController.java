package com.xunji.server.controller.user;

import com.xunji.common.properties.JwtProperties;
import com.xunji.common.result.Result;
import com.xunji.common.utils.JwtUtil;
import com.xunji.pojo.dto.UserLoginDTO;
import com.xunji.pojo.entity.User;
import com.xunji.pojo.vo.UserLoginVO;
import com.xunji.server.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(tags = "用户接口")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;
    /**
     * 用户登录
     *
     * @param userLoginDTO 用户登录信息
     * @return 登录结果
     */
    @PostMapping("/app/login")
    @ApiOperation("App用户登录")
    public Result<UserLoginVO> appLogin(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("App用户登录, 参数: {}", userLoginDTO);

        // 调用业务层登录方法
        User user = userService.appLogin(userLoginDTO);

        //jwt令牌
        Map<String,Object> claims = new HashMap<>();
        claims.put("USER_ID",user.getId());
        String jwt = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .token(jwt)
                .build();

        return Result.success(userLoginVO);
    }

}
