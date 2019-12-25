package com.tensquare.friend.dao;


import com.tensquare.friend.pojo.NotFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NotFriendDao extends JpaRepository<NotFriend,String>,JpaSpecificationExecutor<NotFriend> {


}
