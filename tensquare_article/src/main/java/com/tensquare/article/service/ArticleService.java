package com.tensquare.article.service;

import com.tensquare.article.dao.ArticleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;
    /**
     * @author: zhangyu
     * @date: 2019-12-17
     * @param: [articleid]
     * @return: void
     * 功能描述: 文章审核
     */
    public void examine(String articleid) {
        articleDao.examine(articleid);
    }

    /**
     * @author: zhangyu
     * @date: 2019-12-17
     * @param: [articleid]
     * @return: void
     * 功能描述: 文章点赞
     */
    public void thumbup(String articleid) {
        articleDao.thumbup(articleid);
    }
}
