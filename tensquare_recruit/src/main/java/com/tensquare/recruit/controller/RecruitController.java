package com.tensquare.recruit.controller;

import com.tensquare.recruit.service.RecruitService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Statement;

@RestController
@RequestMapping("recruit")
public class RecruitController {
    @Autowired
    private RecruitService recruitService;

    /**
     * @author: zhangyu
     * @date: 2019-12-17
     * @param: []
     * @return: entity.Result
     * 功能描述: 查询前4条推荐职位
     */
    @GetMapping("search/recommend")
    public Result findRecommend(){
        return new Result(true,StatusCode.OK,"查询推荐成功",recruitService.findByRecommend());
    }
    /**
     * @author: zhangyu
     * @date: 2019-12-17
     * @param: []
     * @return: entity.Result
     * 功能描述: 查询最新的12条职位
     */
    @GetMapping("search/newlist")
    public Result findNewList(){
       return new Result(true,StatusCode.OK,"查询成功",recruitService.findTop12ByStateNotOrderByCreatetimeDesc());
    }
    
}
