package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import entity.IdWorker;
import entity.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SpitService {

    @Autowired
    private SpitDao spitDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private MongoTemplate mongoTemplate;


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
        spit.set_id(idWorker.nextId()+"");
        spit.setComment(0);//回复数
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setState("1");//状态
        spit.setThumbup(0);//点赞数
        spit.setPublishtime(new Date()+"");
        //如果当前吐槽的父id存在，就修改此父id所在的哪条记录的回复数为1
        if (StringUtils.isNotBlank(spit.getParentid())){
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            //修改回复为1
            update.inc("comment",1);
            mongoTemplate.updateFirst(query,update,"spit");
        }
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

    //通过id进行吐槽的查询
    public Spit findById(String spitId) {
        return spitDao.findById(spitId).get();
    }

    //通过父id查询当前页
    public PageResult<Spit> findByParentId(String parentId,int page, int size){
        Page<Spit> pages = spitDao.findByParentid(parentId, PageRequest.of(page, size));
        return new PageResult<Spit>(pages.getTotalElements(),pages.getContent());
    }

    //点赞方法
    public void updateThumbup(String spitId){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitId));
        Update update = new Update();
        update.inc("thumbup",1);
        mongoTemplate.updateFirst(query,update,"spit");
    }
}
