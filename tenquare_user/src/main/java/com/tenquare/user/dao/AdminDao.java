package com.tenquare.user.dao;

import com.tenquare.user.pojo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDao extends JpaRepository<Admin,String> {
    Admin findByLoginname(String loginname);
}
