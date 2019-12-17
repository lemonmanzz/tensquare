package com.tensquare.article.dao;

import com.tensquare.article.pojo.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional//删除和修改必须在此处添加此注解，表示执行事务
public interface ArticleDao extends JpaSpecificationExecutor<Article>,JpaRepository<Article,String> {
    //文章审核
    @Modifying
    @Query(nativeQuery = true, value = "update tb_article set state='1' where id=?1")
    void examine(String articleid);

    //文章点赞
    @Modifying
    @Query(nativeQuery = true, value = "update tb_article set thumbup=thumbup+1 where id=?1")
    void thumbup(String articleid);
}
