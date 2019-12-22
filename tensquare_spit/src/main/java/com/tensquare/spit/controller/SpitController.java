package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("spit")
public class SpitController {

    @Autowired
    private SpitService spitService;
    @Autowired
    private RedisTemplate redisTemplate;


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
     * @date: 2019/12/22
     * @param: [spitId]
     * @return: entity.Result
     * 功能描述: 通过id进行查询
     */
    @GetMapping("{spitId}")
    public Result findById(@PathVariable String spitId){
        return new Result(true,StatusCode.OK,"查询成功",spitService.findById(spitId));
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

    /**
     * @author: zhangyu
     * @date: 2019/12/22
     * @param: [spit]
     * @return: entity.Result
     * 功能描述: 添加吐槽
     */
    @PostMapping
    public Result add(@RequestBody Spit spit){
        spitService.add(spit);
        return new Result(true,StatusCode.OK,"添加吐槽成功");
    }
    /**
     * @author: zhangyu
     * @date: 2019/12/22
     * @param: [spitId]
     * @return: entity.Result
     * 功能描述: 通过id删除吐槽
     */
    @DeleteMapping("{spitId}")
    public Result delete(@PathVariable String spitId){
        spitService.delete(spitId);
        return new Result(true,StatusCode.OK,"删除吐槽成功");
    }

    /**
     * @author: zhangyu
     * @date: 2019/12/22
     * @param: [spitId, spit]
     * @return: entity.Result
     * 功能描述: 更新吐槽
     */
    @PutMapping("{spitId}")
    public Result update(@PathVariable String spitId,@RequestBody Spit spit){
        spit.set_id(spitId);
        spitService.update(spit);
        return new Result(true,StatusCode.OK,"修改吐槽成功");
    }

    /**
     * @author: zhangyu
     * @date: 2019/12/22
     * @param: [parentid, page, size]
     * @return: entity.Result
     * 功能描述: 通过父id分页查询评论
     */
    @GetMapping("/comment/{parentid}/{page}/{size}")
    public Result findByParentId(@PathVariable String parentid,@PathVariable int page, @PathVariable int size){
        PageResult<Spit> pageResult = spitService.findByParentId(parentid, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }

    /**
     * @author: zhangyu
     * @date: 2019/12/22
     * @param: [spitId]
     * @return: entity.Result
     * 功能描述: 进行点赞
     */
    @PutMapping("/thumbup/{spitId}")
    public Result updateThumbup(@PathVariable String spitId){
        try {
            String userid = "zhangsan";
            String thumbup = (String) redisTemplate.opsForValue().get("thumbup_"+userid+"_"+spitId);
            if (StringUtils.isNotBlank(thumbup)){
                return new Result(false,StatusCode.ERROR,"您已经点赞过了");
            }
            spitService.updateThumbup(spitId);
            //此处的1表示该用户已经点赞过
            redisTemplate.opsForValue().set("thumbup_"+userid+"_"+spitId,"1");
            return new Result(true,StatusCode.OK,"点赞成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR,"点赞失败");
        }

    }

}
