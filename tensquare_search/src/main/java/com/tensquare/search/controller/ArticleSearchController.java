package com.tensquare.search.controller;

import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleSearchService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("article")
@CrossOrigin
public class ArticleSearchController {

    @Autowired
    private ArticleSearchService articleSearchService;

    /**
     * @author: zhangyu
     * @date: 2019/12/22
     * @param: [article]
     * @return: entity.Result
     * 功能描述: 添加
     */
    @PostMapping
    public Result add(@RequestBody Article article){
        articleSearchService.add(article);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    @GetMapping("/search/{keywords}/{page}/{size}")
    public Result findByKeywords(@PathVariable String keywords,@PathVariable int page, @PathVariable int size){
        return new Result(true,StatusCode.OK,"搜索完成",articleSearchService.findByTitleOrContentLike(keywords,page,size));
    }

}
