package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FriendDao extends JpaRepository<Friend,String>,JpaSpecificationExecutor {

    //通过用户名id和friendid查询
    Friend findByUseridEqualsAndFriendidEquals(String userId,String friendId);

    @Modifying
    @Query(nativeQuery = true,value = "update tb_friend set islike=?3 where userid=?1 and friendid=?2")
    void updateIsLike(String userId, String friendId,String x);

}
