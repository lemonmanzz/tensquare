package com.tensquare.recruit.controller;

import com.tensquare.recruit.service.EnterpriseService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("enterprise")
public class EnterpriseController {
    @Autowired
    private EnterpriseService enterpriseService;
    @GetMapping("search/hotlist")
    public Result findHotList(){
        return new Result(true,StatusCode.OK,"查询成功",enterpriseService.findHotList());
    }
}
