package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FriendDao extends JpaRepository<Friend,String>,JpaSpecificationExecutor {

    //通过用户名id和friendid查询
    Friend findByUseridEqualsAndFriendidEquals(String userId,String friendId);

}
