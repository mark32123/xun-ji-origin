package com.xunji.server.interceptor;

import com.xunji.common.constant.JwtClaimsConstant;
import com.xunji.common.context.BaseContext;
import com.xunji.common.properties.JwtProperties;
import com.xunji.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt令牌校验的拦截器
 */
@Component
@Slf4j
public class JwtTokenUserInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 校验jwt
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }

        //1、从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getUserTokenName());
        log.info("获取到的token: {}", token);

        // 支持两种格式：带Bearer前缀和不带前缀
        if (token == null) {
            log.warn("Token为空");
            response.setStatus(401);
            return false;
        }

        // 如果token以Bearer开头，则去除前缀
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }


        //2、校验令牌
        try {
            log.info("jwt校验:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);

            /****/
            log.info("解析后的claims内容: {}", claims);// 添加日志查看
            Object userIdObj = claims.get(JwtClaimsConstant.USER_ID);
            if (userIdObj == null) {
                log.warn("JWT中缺少用户ID信息，claims内容: {}", claims);
                response.setStatus(401);
                return false;
            }
            /****/

            Long userId = Long.valueOf(claims.get(JwtClaimsConstant.USER_ID).toString());
            log.info("当前用户id：{}", userId);
            BaseContext.setCurrentId(userId);
            //3、通过，放行
            return true;
        } // 在 JwtTokenUserInterceptor 中改进异常处理
            catch (Exception ex) {
                log.error("JWT校验失败: ", ex);
                response.setStatus(401);
                return false;
            }

    }
}
