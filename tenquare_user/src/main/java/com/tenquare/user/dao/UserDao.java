package com.tenquare.user.dao;


import com.tenquare.user.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User,String> {
    User findByLoginname(String loginname);

    @Modifying
    @Query(nativeQuery = true,value = "update tb_user set fanscount=fanscount+?2 where id=?1")
    void updateFans(String userid, int x);

    @Modifying
    @Query(nativeQuery = true,value = "update tb_user set followcount=followcount+?2 where id=?1")
    void updateFollow(String userid, int x);
}
