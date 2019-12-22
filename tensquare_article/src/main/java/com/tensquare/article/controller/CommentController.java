package com.tensquare.article.controller;

import com.tensquare.article.pojo.Comment;
import com.tensquare.article.service.CommentService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * @author: zhangyu
     * @date: 2019/12/22
     * @param: [comment]
     * @return: entity.Result
     * 功能描述: 添加评论
     */
    @PostMapping
    public Result add(@RequestBody Comment comment){
        commentService.add(comment);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    //通过文章id查询评论列表
    @GetMapping("{articleId}")
    public Result findByArticleId(@PathVariable String articleId){
        return new Result(true,StatusCode.OK,"查询成功",commentService.findByArticleId(articleId));
    }

    //通过id删除评论
    @DeleteMapping("{id}")
    public Result deletById(@PathVariable String id){
        commentService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }
}
