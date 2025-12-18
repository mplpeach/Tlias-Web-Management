package com.itheima.interceptor;

import com.itheima.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 令牌校验拦截器
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // 1.获取请求路径
//        String path = request.getRequestURI();
//        if (path.contains("login")) {
//            // 2.放行
//            log.info("登录请求，放行：{}", path);
//            return true;
//        }
        // 3.获取token
        String token = request.getHeader("token");
        // 4.判断token是否为空
        if (token == null || token.isEmpty()) {
            // 5.返回错误信息（401状态码）
            log.info("请求头token为空，返回401");
            response.setStatus(401);
            return false;
        }
        // 6.判断token是否正确
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            // 7.返回错误信息（401状态码）
            log.info("token解析失败，返回401");
            response.setStatus(401);
            return false;
        }
        // 8.放行
        log.info("token正确，放行");
        return true;
    }
}
