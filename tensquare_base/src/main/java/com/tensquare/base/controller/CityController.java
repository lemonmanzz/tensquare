package com.tensquare.base.controller;

import com.tensquare.base.pojo.City;
import com.tensquare.base.pojo.City;
import com.tensquare.base.service.CityService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("city")
public class CityController {

    @Autowired
    private CityService cityService;
    /**
     * @author: zhangyu
     * @date: 2019-12-16
     * @param: []
     * @return: entity.Result
     * 功能描述: 查询所有的城市
     */
    @GetMapping
    public Result findAll(){
        try {
            List<City> cityList = cityService.findAll();
            return new Result(true,StatusCode.OK,"查询所有城市成功",cityList);
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
     * 功能描述: 查询有效的所有城市
     */
    @GetMapping("list")
    public Result list(){
        try {
            List<City> cityList = cityService.findByStatus("1");
            return new Result(true,StatusCode.OK,"查询有效城市成功",cityList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR,"查询失败!");
        }
    }

    /**
     * @author: zhangyu
     * @date: 2019-12-16
     * @param: [city]
     * @return: entity.Result
     * 功能描述: 添加城市
     */
    @PostMapping
    public Result add(@RequestBody City city){
        try {
            cityService.add(city);
            return new Result(true,StatusCode.OK,"添加城市成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR,"添加城市失败");
        }
    }

    /**
     * @author: zhangyu
     * @date: 2019-12-16
     * @param: [cityId, city]
     * @return: entity.Result
     * 功能描述: 修改城市
     */
    @PutMapping("{cityId}")
    public Result update(@PathVariable String cityId, @RequestBody City city){
        try {
            city.setId(cityId);
            cityService.update(city);
            return new Result(true,StatusCode.OK,"修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR,"修改失败");
        }
    }

    /**
     * @author: zhangyu
     * @date: 2019-12-16
     * @param: [cityId]
     * @return: entity.Result
     * 功能描述: 通过城市id删除城市
     */
    @DeleteMapping("{cityId}")
    public Result delete(@PathVariable String cityId){
        try {
            cityService.deleteById(cityId);
            return new Result(true,StatusCode.OK,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR,"删除失败");
        }

    }
}
