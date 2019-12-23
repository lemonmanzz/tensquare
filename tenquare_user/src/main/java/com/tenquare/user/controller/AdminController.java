package com.tenquare.user.controller;

import com.tenquare.user.pojo.Admin;
import com.tenquare.user.service.AdminService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public Result login(@RequestBody Admin admin){

        if ( adminService.findByLoginnameAndPassword(admin.getLoginname(), admin.getPassword()) != null){
            return new Result(true,StatusCode.OK,"登录成功");
        }
        return new Result(false,StatusCode.ERROR,"登录失败");
    }

    @PostMapping("add")
    public Result add(@RequestBody Admin admin){
        adminService.add(admin);
        return new Result(true,StatusCode.OK,"添加管理员成功");
    }
}
