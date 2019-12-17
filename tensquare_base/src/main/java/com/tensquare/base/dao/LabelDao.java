package com.tensquare.base.dao;

import com.tensquare.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * @author: zhangyu
 * @date: 2019-12-16
 * @param:
 * @return:
 * 功能描述: 创建dao接口，代理实现jpa的crud
 */

public interface LabelDao extends JpaRepository<Label,String>,JpaSpecificationExecutor {
}
