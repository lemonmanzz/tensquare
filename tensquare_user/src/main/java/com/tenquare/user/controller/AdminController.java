package com.tenquare.user.controller;

import com.tenquare.user.pojo.Admin;
import com.tenquare.user.service.AdminService;
import config.JwtUtil;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private HttpServletRequest request;

    /**
     * @author: zhangyu
     * @date: 2019-12-24
     * @param: [admin]
     * @return: entity.Result
     * 功能描述: 管理员登陆的
     */
    @PostMapping("/login")
    public Result login(@RequestBody Admin admin){
        admin = adminService.findByLoginnameAndPassword(admin.getLoginname(), admin.getPassword());
        if (admin != null){
            //得到token
            String token = jwtUtil.createToken(admin.getId(), admin.getLoginname(), "admin");
            //放入map中
            Map map = new HashMap<>();
            map.put("token",token);
            map.put("name",admin.getLoginname());
            //返回
            return new Result(true,StatusCode.OK,"登录成功",map);
        }
        return new Result(false,StatusCode.ERROR,"登录失败");
    }

    /**
     * @author: zhangyu
     * @date: 2019-12-24
     * @param: [admin]
     * @return: entity.Result
     * 功能描述: 添加管理员的方法
     */
    @PostMapping("add")
    public Result add(@RequestBody Admin admin){
        adminService.add(admin);
        return new Result(true,StatusCode.OK,"添加管理员成功");
    }

    @DeleteMapping("{adminId}")
    public Result delete(@PathVariable String adminId){
        try {
            Claims claims = (Claims) request.getAttribute("claims");
            if (claims != null && claims.get("roles").equals("admin")){
                adminService.deleteById(adminId);
                return new Result(true,StatusCode.OK,"删除成功");
            }
            return new Result(false,StatusCode.ERROR,"无删除权限");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR,"删除失败 ");
        }
    }
}
