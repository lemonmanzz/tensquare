package com.tensquare.recruit.dao;


import com.tensquare.recruit.pojo.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EnterpriseDao extends JpaRepository<Enterprise,String>, JpaSpecificationExecutor<Enterprise> {
   //此处的参数必须要要有构造方法
    List<Enterprise> findAllByIshot(String ishot);
}
