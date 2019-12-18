package com.tensquare.gathering.service;

import com.tensquare.gathering.dao.GatheringDao;
import com.tensquare.gathering.pojo.Gathering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class GatheringService {
    @Autowired
    private GatheringDao gatheringDao;

    /**
     * @author: zhangyu
     * @date: 2019-12-18
     * @param: [id]
     * @return: com.tensquare.gathering.pojo.Gathering
     * 功能描述:
     */
    @Cacheable(value = "gathering",key = "#id")
    public Gathering findById(String id){
        System.out.println("正在查询数据。。。。。。。");
       return gatheringDao.findById(id).get();
    }

    @CacheEvict(value = "gathering",key = "#gathering.id")
    public void update(Gathering gathering){
        gatheringDao.save(gathering);
    }

    @CacheEvict(value = "gathering",key = "#id")
    public void delete(String id){
        gatheringDao.deleteById(id);
    }
}
