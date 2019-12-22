package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpitDao extends MongoRepository<Spit,String> {
    /**
     * @author: zhangyu
     * @date: 2019/12/22
     * @param: [parentid, pageable]
     * @return: org.springframework.data.domain.Page<com.tensquare.spit.pojo.Spit>
     * 功能描述: 通过
     */
    public Page<Spit> findByParentid(String parentid, Pageable pageable);
}
