package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("spit")
public class SpitController {

    @Autowired
    private SpitService spitService;


    /**
     * @author: zhangyu
     * @date: 2019-12-18
     * @param: []
     * @return: entity.Result
     * 功能描述: 查询所有的吐槽
     */
    @GetMapping
    public Result findAll(){
        return new Result(true,StatusCode.OK,"查询全部成功",spitService.findAll());
    }

    /**
     * @author: zhangyu
     * @date: 2019-12-18
     * @param: [spit]
     * @return: entity.Result
     * 功能描述: 待条件查询
     */
    @GetMapping("search")
    public Result search(@RequestBody Spit spit){
        return new Result(true,StatusCode.OK,"条件查询",spitService.search(spit));
    }

    @PostMapping
    public Result add(@RequestBody Spit spit){
        spitService.add(spit);
        return new Result(true,StatusCode.OK,"添加吐槽成功");
    }
    @DeleteMapping("{spitId}")
    public Result delete(@PathVariable String spitId){
        spitService.delete(spitId);
        return new Result(true,StatusCode.OK,"删除吐槽成功");
    }
    @PutMapping("{spitId}")
    public Result update(@PathVariable String spitId,@RequestBody Spit spit){
        spit.set_id(spitId);
        spitService.update(spit);
        return new Result(true,StatusCode.OK,"修改吐槽成功");
    }
}
