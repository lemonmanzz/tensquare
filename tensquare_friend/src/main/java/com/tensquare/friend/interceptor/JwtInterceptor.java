package com.tensquare.friend.interceptor;

import config.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * @author: zhangyu
     * @date: 2019-12-25
     * @param: [request, response, handler]
     * @return: boolean
     * 功能描述: 将请求头中的token信息转换为claims对象并放入请求作用域中
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //获得请求头带的token字符串
        String authorization = request.getHeader("Authorization");
        //判断是否为空
        if (StringUtils.isNotBlank(authorization)){
            //判断是否以’Bearer ‘开头
            if (authorization.startsWith("Bearer ")){
                //得到令牌token
                String token = authorization.substring(7);
                //对token进行解析，得到claims
                Claims claims = jwtUtil.parseToken(token);
                if (claims != null){
                    //将claims放入请求作用域中
                    request.setAttribute("claim",claims);
                }
            }
        }
        return true;
    }
}
