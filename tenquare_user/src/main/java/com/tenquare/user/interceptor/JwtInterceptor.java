package com.tenquare.user.interceptor;

import config.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: zhangyu
 * @date: 2019-12-24
 * @param:
 * @return:
 * 功能描述: 定义过滤器
 */
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //得到Authorization的对应的token字符串
        String tokenStr = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(tokenStr) && tokenStr.startsWith("Bearer ")){
            String token = tokenStr.substring(7);
            //解析token
            Claims claims = jwtUtil.parseToken(token);
            //判断是否为空
            if (claims != null){
                request.setAttribute("claims",claims);
            }
        }

        return true;
    }
}
