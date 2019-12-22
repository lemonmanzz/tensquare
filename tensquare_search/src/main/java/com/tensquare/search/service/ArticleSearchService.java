package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleSearchDao;
import com.tensquare.search.pojo.Article;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ArticleSearchService  {
    @Autowired
    private ArticleSearchDao articleSearchDao;

    //添加文章
    public void add(Article article){
        articleSearchDao.save(article);
    }

    //通过关键字进行查询
    public PageResult<Article> findByTitleOrContentLike(String keywords,int page,int size){
        Page<Article> pages = articleSearchDao.findByTitleOrContentLike(keywords, keywords, PageRequest.of(page, size));
        return new PageResult<>(pages.getTotalElements(),pages.getContent());
    }
}
