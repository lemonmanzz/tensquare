package com.tensquare.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import config.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class MangerFilter extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String filterType() {
        return "pre";  //pre表示在路由之前就进行操作
    }

    @Override
    public int filterOrder() {
        return 0; //越小表示优先级越高
    }

    @Override
    public boolean shouldFilter() {
        //表示是否进行过滤
        return true;
    }

    @Override
    public Object run() throws ZuulException {//过滤器的实际代码部分
        //获取请求上下文对象
        RequestContext currentContext = RequestContext.getCurrentContext();
        //获取请求对象
        HttpServletRequest request = currentContext.getRequest();
        //对一部分资源进行放行
        if (request.getMethod().equals("OPTIONS")){
            System.out.println("oppp");
            return null; //放行
        }

        if (request.getRequestURI().contains("/login")){
            return null;//表示放行
        }
        //获得请求头
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(authorization)){
            //得到token
            if (authorization.startsWith("Bearer ")){
                String token = authorization.substring(7);
                //解析token
                Claims claims = jwtUtil.parseToken(token);
                if (claims != null){
                    //得到角色
                    String roles = (String) claims.get("roles");
                    //判断是否是管理员
                    if (StringUtils.isNotBlank(roles) && roles.equals("admin")){
                        //将请求头设置到向下响应中请求头中
                        currentContext.addZuulRequestHeader("Authorization",authorization);
                        System.out.println("经过了后台过滤器");
                        //放行
                        return null;
                    }
                }
            }
        }
        //其他情况不放行
        currentContext.setSendZuulResponse(false);
        currentContext.setResponseBody("无权访问");
        currentContext.setResponseStatusCode(403);
        currentContext.getResponse().setContentType("text/html;charset=utf-8");
        return null;
    }
}
