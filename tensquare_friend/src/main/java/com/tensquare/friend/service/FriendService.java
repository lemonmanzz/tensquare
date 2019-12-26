package com.tensquare.friend.service;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.pojo.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FriendService {
    @Autowired
    private FriendDao friendDao;
    @Autowired
    private UserClient userClient;

    /**
     * @author: zhangyu
     * @date: 2019-12-25
     * @param: [userId, friendId, type]
     * @return: void
     * 功能描述: 添加好友service实现
     *      参数一：用户id
     *      参数二：好友id
     */
    public void addFriend(String userId, String friendId) {
        //首先，通过用户名和好友id进行查库，如果存在则抛出异常
        Friend friend = friendDao.findByUseridEqualsAndFriendidEquals(userId, friendId);
        if (friend != null){
            throw new RuntimeException("你已经添加过了");
        }
        //构建好友对象
        friend = new Friend(userId, friendId, "0");
        //保存好友到数据库中
        friendDao.save(friend);
        //反向查询，看看对方是否添加了自己为好友
        Friend friends = friendDao.findByUseridEqualsAndFriendidEquals(friendId, userId);
        if (friends != null){//friends不为空表示对方是自己的好友，修改为互相关注，即两条数据的islike改为 1
            friendDao.updateIsLike(userId,friendId,"1");
            friendDao.updateIsLike(friendId,userId,"1");
        }
        //调用user模块，将对方粉丝数加1，自己的关注数加1
        userClient.updateFans(friendId,1);
        userClient.updateFollow(userId,1);

    }

    /**
     * @author: zhangyu
     * @date: 2019-12-26
     * @param: [id, friendid]
     * @return: void
     * 功能描述: 删除好友,好友表中删除掉用户id&好友id为主键的数据，然后更新粉丝数和关注数
     */
    public void delete(String id, String friendid) {
        //查询是否是好友关系
        Friend friend = friendDao.findByUseridEqualsAndFriendidEquals(id, friendid);
        if (friend == null){
            throw new RuntimeException("您的好友中没有他");
        }
        //删除
        friendDao.delete(friend);
        //反向查询
        Friend friends = friendDao.findByUseridEqualsAndFriendidEquals(friendid, id);
        if (friends != null){
            //修改好友列表的islike为0
            friendDao.updateIsLike(friendid,id,"0");
        }
        //更新粉丝数和关注数
        userClient.updateFollow(id,-1);
        userClient.updateFans(friendid,-1);
    }
}
