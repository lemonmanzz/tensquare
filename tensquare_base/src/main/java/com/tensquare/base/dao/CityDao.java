package com.tensquare.base.dao;

import com.tensquare.base.pojo.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
/**
 * @author: zhangyu
 * @date: 2019-12-16
 * @param:
 * @return:
 * 功能描述: 使用接口代理生成crud
 */
public interface CityDao extends JpaSpecificationExecutor<City>, JpaRepository<City,String> {

}
