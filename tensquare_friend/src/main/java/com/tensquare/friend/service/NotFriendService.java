package com.tensquare.friend.service;

import com.tensquare.friend.dao.NotFriendDao;
import com.tensquare.friend.pojo.NotFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NotFriendService {

    @Autowired
    private NotFriendDao  notFriendDao;
    public void addNotFriend(String userId, String friendId) {
        //保存到非好友表
        NotFriend notFriend = new NotFriend(userId, friendId);
        notFriendDao.save(notFriend);
    }
}
