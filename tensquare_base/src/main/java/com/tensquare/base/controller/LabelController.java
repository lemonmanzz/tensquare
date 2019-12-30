package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("label")
@RefreshScope
public class LabelController {
    @Autowired
    private LabelService labelService;
    @Value("${ip}")
    private String ip;
    /**
     * @author: zhangyu
     * @date: 2019-12-16
     * @param: []
     * @return: entity.Result
     * 功能描述: 查询所有的标签
     */
    @GetMapping
    public Result findAll(){
        System.out.println("ip = " + ip);
        try {
            List<Label> labelList = labelService.findAll();
            return new Result(true,StatusCode.OK,"查询所有标签成功",labelList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR,"查询失败!");
        }
    }
    /**
     * @author: zhangyu
     * @date: 2019-12-16
     * @param: []
     * @return: entity.Result
     * 功能描述: 查询有效的所有标签
     */
    @GetMapping("list")
    public Result list(){
        try {
            List<Label> labelList = labelService.findByStatus("1");
            return new Result(true,StatusCode.OK,"查询有效标签成功",labelList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR,"查询失败!");
        }
    }

    /**
     * @author: zhangyu
     * @date: 2019-12-16
     * @param: [label]
     * @return: entity.Result
     * 功能描述: 添加标签
     */
    @PostMapping
    public Result add(@RequestBody Label label){
        try {
            labelService.add(label);
            return new Result(true,StatusCode.OK,"添加标签成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR,"添加标签失败");
        }
    }
    /**
     * @author: zhangyu
     * @date: 2019-12-17
     * @param: [searchLabel]
     * @return: entity.Result
     * 功能描述: 条件查询
     */
    @PostMapping("search")
    public Result search(@RequestBody Label searchLabel){
        return new Result(true,StatusCode.OK,"条件查询成功",labelService.search(searchLabel));
    }
    /**
     * @author: zhangyu
     * @date: 2019-12-17
     * @param: [page, size, searchLabel]
     * @return: entity.Result
     * 功能描述: 条件查询带分页
     */
    @PostMapping("search/{page}/{size}")
    public Result search(@PathVariable int page, @PathVariable int size, @RequestBody Label searchLabel){
        return new Result(true,StatusCode.OK,"查询成功",labelService.search(page,size,searchLabel));
    }

    /**
     * @author: zhangyu
     * @date: 2019-12-16
     * @param: [labelId, label]
     * @return: entity.Result
     * 功能描述: 修改标签
     */
    @PutMapping("{labelId}")
    public Result update(@PathVariable String labelId, @RequestBody Label label){
        try {
            label.setId(labelId);
            labelService.update(label);
            return new Result(true,StatusCode.OK,"修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR,"修改失败");
        }
    }

    /**
     * @author: zhangyu
     * @date: 2019-12-16
     * @param: [labelId]
     * @return: entity.Result
     * 功能描述: 通过标签id删除标签
     */
    @DeleteMapping("{labelId}")
    public Result delete(@PathVariable String labelId){
        try {
            labelService.deleteById(labelId);
            return new Result(true,StatusCode.OK,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR,"删除失败");
        }

    }
    @GetMapping("{id}")
    public Result findById(@PathVariable String id){
        System.out.println("这是第二个基础服务");
        return new Result(true,StatusCode.ERROR,"查询成功",labelService.findById(id));
    }


}
