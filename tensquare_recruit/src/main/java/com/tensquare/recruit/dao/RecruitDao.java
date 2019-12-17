package com.tensquare.recruit.dao;

import com.tensquare.recruit.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RecruitDao extends JpaSpecificationExecutor<Recruit>,JpaRepository<Recruit,String> {

    //职位推荐查询
    List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String state);
    //查询所有职位前12个，即state不是0
    List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc(String state);
}
