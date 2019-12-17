package com.tensquare.qa.dao;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem> {

    //获得最新问答列表
    @Query(nativeQuery = true,
            value = "select p.* from tb_pl l,tb_problem p where p.id=l.problemid and l.labelid=?1 order by createtime desc")
    List<Problem> getNewList(String labelid, Pageable pageable);

    //获得热门问答列表
    @Query(nativeQuery = true, value = "select p.* from tb_pl l,tb_problem p where p.id=l.problemid and l.labelid=?1 order by reply desc")
    List<Problem> getHotList(String labelid, PageRequest of);
}
