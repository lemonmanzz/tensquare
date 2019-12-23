package com.tenquare.user.service;

import com.tenquare.user.dao.UserDao;
import com.tenquare.user.pojo.User;
import entity.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder encoder;

    //发送短信验证码
    public void sendMessage(String mobile){
        //生成验证码
        Random random = new Random();
        int code = random.nextInt(999999-100000)+100000;
        //将验证码存储在redis中
        redisTemplate.opsForValue().set(mobile,code+"",5,TimeUnit.MINUTES);
        //发送消息
        //1.构建发送的map
        HashMap map = new HashMap<>();
        map.put("code",code);
        map.put("mobile",mobile);
        rabbitTemplate.convertAndSend("sms",map);
    }

    //注册方法
    public void register(User user, String code){
        //获取redis中验证码
        String vilateCode =  redisTemplate.opsForValue().get(user.getMobile()).toString();
        //判断连个验证码是否一致
        if (!StringUtils.isNotBlank(vilateCode)){
            throw new RuntimeException("请先获取手机验证码");
        }
        if (!vilateCode.equals(code)){
            throw new RuntimeException("验证码不正确");
        }
        user.setId( idWorker.nextId()+"" );
        user.setFollowcount(0L);//关注数
        user.setFanscount(0L);//粉丝数
        user.setOnline(0L);//在线时长
        user.setRegdate(new Date());//注册日期
        user.setUpdatedate(new Date());//更新日期
        user.setLastdate(new Date());//最后登陆日期
        //加密
        user.setPassword(encoder.encode(user.getPassword()));
        //添加到数据库
        userDao.save(user);
    }

    public User login(User user) {
        User userDaoByLoginname = userDao.findByLoginname(user.getLoginname());
        if (userDaoByLoginname != null && encoder.matches(user.getPassword(),userDaoByLoginname.getPassword())){
            return userDaoByLoginname;
        }
        return null;
    }
}
