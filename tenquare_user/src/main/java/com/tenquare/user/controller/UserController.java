package com.tenquare.user.controller;

import com.tenquare.user.pojo.User;
import com.tenquare.user.service.UserService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * @author: zhangyu
     * @date: 2019-12-23
     * @param: [mobile]
     * @return: entity.Result
     * 功能描述: 发送短信验证码
     */
    @PostMapping("sendsms/{mobile}")
    public Result sendMessage(@PathVariable String mobile){
        userService.sendMessage(mobile);
        return new Result(true,StatusCode.OK,"短信发送成功");
    }
    /**
     * @author: zhangyu
     * @date: 2019-12-23
     * @param: [code, user]
     * @return: entity.Result
     * 功能描述: 用户注册
     */
    @PostMapping("register/{code}")
    public Result register(@PathVariable String code, @RequestBody User user){
        try {
            userService.register(user,code);
            return new Result(true,StatusCode.OK,"用户注册成功");
        }catch (Exception e){
            return new Result(false,StatusCode.OK,e.getMessage());
        }
        
    }

    @PostMapping("login")
    public Result login(@RequestBody User user){
        User loginUser = userService.login(user);
        if (loginUser != null){
            return new Result(true,StatusCode.OK,"登录成功");
        }
        return new Result(false,StatusCode.ERROR,"登录失败");
    }

}
