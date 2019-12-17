package com.tensquare.article.controller;

import com.tensquare.article.service.ArticleService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * @author: zhangyu
     * @date: 2019-12-17
     * @param: [articleid]
     * @return: entity.Result
     * 功能描述: 文章审核
     */
    @PutMapping("examine/{articleid}")
    public Result examine(@PathVariable String articleid){
        articleService.examine(articleid);
        return new Result(true,StatusCode.OK,"文章审核完成!");
    }
    /**
     * @author: zhangyu
     * @date: 2019-12-17
     * @param: [articleid]
     * @return: entity.Result
     * 功能描述: 文章点赞
     */
    @PutMapping("/thumbup/{articleid}")
    public Result thumbup(@PathVariable String articleid){
        articleService.thumbup(articleid);
        return new Result(true,StatusCode.OK,"点赞成功!");
    }
}
