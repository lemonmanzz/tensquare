package com.tensquare.qa.controller;

import com.tensquare.qa.service.ProblemService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("problem")
public class ProblemController {
    @Autowired
    private ProblemService problemService;

    /**
     * @author: zhangyu
     * @date: 2019-12-17
     * @param: [labelid, page, size]
     * @return: entity.Result
     * 功能描述: 查询最新问答列表
     */
    @GetMapping("newlist/{labelid}/{page}/{size}")
    public Result newlist(@PathVariable String labelid, @PathVariable int page, @PathVariable int size){
        return new Result(true,StatusCode.OK,"获得最新问答列表成功",problemService.getNewList(labelid,page,size));
    }
    /**
     * @author: zhangyu
     * @date: 2019-12-17
     * @param: [labelid, page, size]
     * @return: entity.Result
     * 功能描述: 查找热门问答列表
     */
    @GetMapping("hotlist/{labelid}/{page}/{size}")
    public Result hotlist(@PathVariable String labelid, @PathVariable int page, @PathVariable int size){
        return new Result(true,StatusCode.OK,"获得热门问答列表成功",problemService.getHotList(labelid,page,size));
    }
}
