package com.tensquare.friend.service;

import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.pojo.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendService {
    @Autowired
    private FriendDao friendDao;

    /**
     * @author: zhangyu
     * @date: 2019-12-25
     * @param: [userId, friendId, type]
     * @return: void
     * 功能描述: 添加好友service实现
     *      参数一：用户id
     *      参数二：好友id
     *      参数三：1 喜欢 ，2 不喜欢
     */
    public void addFriend(String userId, String friendId) {
        //首先，通过用户名和好友id进行查库，如果存在则抛出异常
        Friend friend = friendDao.findByUseridEqualsAndFriendidEquals(userId, friendId);
        if (friend != null){
            throw new RuntimeException("你已经添加过了");
        }
    }
}
