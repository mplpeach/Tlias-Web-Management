package com.itheima.filter;

import com.itheima.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 1.获取请求路径
        String path = request.getRequestURI();
        if (path.contains("login")) {
            // 2.放行
            log.info("登录请求，放行：{}", path);
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        // 3.获取token
        String token = request.getHeader("token");
        // 4.判断token是否为空
        if (token == null || token.isEmpty()) {
            // 5.返回错误信息（401状态码）
            log.info("请求头token为空，返回401");
            response.setStatus(401);
            return;
        }
        // 6.判断token是否正确
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            // 7.返回错误信息（401状态码）
            log.info("token解析失败，返回401");
            response.setStatus(401);
            return;
        }
        // 8.放行
        log.info("token正确，放行：{}", path);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
