package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpitService {

    @Autowired
    private SpitDao spitDao;

    //查询所有吐槽
    public List<Spit> findAll() {
        return spitDao.findAll();
    }

    //条件查询
    public List<Spit> search(Spit spit) {
       return null;
    }

    //添加吐槽
    public void add(Spit spit) {
        spitDao.save(spit);
    }

    //删除
    public void delete(String spitId) {
        spitDao.deleteById(spitId);
    }

    //修改
    public void update(Spit spit) {
        spitDao.save(spit);
    }
}
