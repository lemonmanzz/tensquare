package com.tensquare.article.service;

import com.tensquare.article.dao.CommentDao;
import com.tensquare.article.pojo.Comment;
import entity.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private IdWorker idWorker;

    //添加评论
    public void  add(Comment comment){
        comment.set_id(idWorker.nextId()+"");
        commentDao.save(comment);
    }

    //通过文章id查询评论
    public List<Comment> findByArticleId(String articleId) {
        return commentDao.findByArticleid(articleId);
    }

    //通过id删除评论
    public void deleteById(String id) {
        commentDao.deleteById(id);
    }
}
