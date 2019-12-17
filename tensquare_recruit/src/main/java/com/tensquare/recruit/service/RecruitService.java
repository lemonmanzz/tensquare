package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.RecruitDao;
import com.tensquare.recruit.pojo.Recruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RecruitService {
    @Autowired
    private RecruitDao recruitDao;

    public List<Recruit> findByRecommend() {
        //state等于2表示推荐
        return recruitDao.findTop4ByStateOrderByCreatetimeDesc("2");
    }

    public List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc() {
        //state不等于0表示所有已经审核的职位
        return recruitDao.findTop12ByStateNotOrderByCreatetimeDesc("0");
    }
}
