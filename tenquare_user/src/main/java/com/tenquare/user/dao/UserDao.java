package com.tenquare.user.dao;


import com.tenquare.user.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,String> {
    User findByLoginname(String loginname);
}
