package com.tenquare.user.controller;

import com.tenquare.user.pojo.User;
import com.tenquare.user.service.UserService;
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
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private HttpServletRequest request;



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
            //创建token
            String token = jwtUtil.createToken(loginUser.getId(), loginUser.getLoginname(), "user");
            Map map =  new HashMap<>();
            map.put("token",token);
            map.put("name",loginUser.getLoginname());
            return new Result(true,StatusCode.OK,"登录成功",map);
        }
        return new Result(false,StatusCode.ERROR,"登录失败");
    }

    @DeleteMapping("{userId}")
    public Result delete(@PathVariable String userId){
        try {
            Claims claims = (Claims) request.getAttribute("claims");
            if (claims != null && claims.get("roles").equals("admin")){
                userService.deleteById(userId);
                return  new Result(true,StatusCode.OK,"删除成功");
            }
            return new Result(false,StatusCode.ERROR,"无权删除");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR,"删除失败");
        }
    }

}
