package com.tensquare.gathering.controller;

import com.tensquare.gathering.pojo.Gathering;
import com.tensquare.gathering.service.GatheringService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gathering")
public class GatheringController {
    @Autowired
    private GatheringService gatheringService;

    /**
     * @author: zhangyu
     * @date: 2019-12-18
     * @param: [gatheringId]
     * @return: entity.Result
     * 功能描述: 通过id查询活动
     */
    @GetMapping("{gatheringId}")
    public Result findById(@PathVariable String gatheringId){
      return new Result(true,StatusCode.OK,"成功",gatheringService.findById(gatheringId));
    }

    @PutMapping("{gatheringId}")
    public Result update(@PathVariable String gatheringId, @RequestBody Gathering gathering){
        gathering.setId(gatheringId);
        gatheringService.update(gathering);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    @DeleteMapping("{gatheringId}")
    public Result delete(@PathVariable String gatheringId){
        gatheringService.delete(gatheringId);
        return new Result(true,StatusCode.OK,"删除活动成功");
    }
}
