package com.tenquare.user.service;

import com.tenquare.user.dao.AdminDao;
import com.tenquare.user.pojo.Admin;
import entity.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private AdminDao adminDao;
    //添加管理员
    public void add(Admin admin){
        admin.setId(idWorker.nextId()+"");
        admin.setPassword(encoder.encode(admin.getPassword()));
        adminDao.save(admin);
    }

    //登录认证
    public Admin findByLoginnameAndPassword(String loginname,String password){
        Admin admin = adminDao.findByLoginname(loginname);
        if (admin != null && encoder.matches(password,admin.getPassword())){
            return admin;
        }
        return null;
    }
}
