package com.tensquare.article.service;

import com.tensquare.article.dao.ArticleDao;
import com.tensquare.article.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private RedisTemplate redisTemplate;
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

    /**
     * @author: zhangyu
     * @date: 2019-12-18
     * @param: [articleId]
     * @return: com.tensquare.article.pojo.Article
     * 功能描述: 根据文章id查询文章，走redis
     */
    public Article findArticleId(String articleId){
       //从缓存中获取文章
        Article article = (Article) redisTemplate.boundHashOps("articleList").get(articleId);
        //判断是否冲缓存库中拿到数据
        if (article != null){
            return article;
        }
        //如果缓存库中不存在，则查询数据库并放入数据库中
        article = articleDao.findById(articleId).get();
        redisTemplate.boundHashOps("articleList").put(article.getId(),article);
        //返回文章对象
        System.out.println("正在从数据库中查询数据。。。。。");
        return article;
    }

    /**
     * @author: zhangyu
     * @date: 2019-12-18
     * @param: [article]
     * @return: void
     * 功能描述: 更新文章，为了保证数据一致性，在此先删除redis中的数据
     */
    public void update(Article article){
        redisTemplate.boundHashOps("articleList").delete(article.getId());
        articleDao.save(article);
    }

    /**
     * @author: zhangyu
     * @date: 2019-12-18
     * @param: [articleId]
     * @return: void
     * 功能描述: 删除文章，为了保证数据一致性，在此先删除redis中的数据
     */
    public void delete(String articleId){
        redisTemplate.boundHashOps("articleList").delete(articleId);
        articleDao.deleteById(articleId);
    }
}
