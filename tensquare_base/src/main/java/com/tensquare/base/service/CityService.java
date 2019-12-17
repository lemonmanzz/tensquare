package com.tensquare.base.service;

import com.tensquare.base.dao.CityDao;
import com.tensquare.base.pojo.City;
import entity.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityDao cityDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * @author: zhangyu
     * @date: 2019-12-16
     * @param: []
     * @return: java.util.List<com.tensquare.base.pojo.City>
     * 功能描述: 查询所有的城市
     */
    public List<City> findAll() {
        return cityDao.findAll();
    }
    /**
     * @author: zhangyu
     * @date: 2019-12-16
     * @param: [city]
     * @return: void
     * 功能描述: 添加城市到数据库
     */
    public void add(City city) {
        //使用idworker获得id
        city.setId(String.valueOf(idWorker.nextId()));
        cityDao.save(city);
    }

    /**
     * @author: zhangyu
     * @date: 2019-12-16
     * @param: [city]
     * @return: void
     * 功能描述: 修改城市
     */
    public void update(City city) {
        cityDao.save(city);
    }

    /**
     * @author: zhangyu
     * @date: 2019-12-16
     * @param: [cityId]
     * @return: void
     * 功能描述: 通过城市id删除城市
     */
    public void deleteById(String cityId) {
        cityDao.deleteById(cityId);
    }

    public List<City> findByStatus(String s) {

        return cityDao.findAll();
    }
}
