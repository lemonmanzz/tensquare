package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import entity.IdWorker;
import entity.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LabelService{
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * @author: zhangyu
     * @date: 2019-12-16
     * @param: []
     * @return: java.util.List<com.tensquare.base.pojo.Label>
     * 功能描述: 查询所有的标签
     */

    public List<Label> findAll() {
        return labelDao.findAll();
    }
    /**
     * @author: zhangyu
     * @date: 2019-12-16
     * @param: [label]
     * @return: void
     * 功能描述: 添加标签到数据库
     */

    public void add(Label label) {
        //使用idworker获得id
        label.setId(String.valueOf(idWorker.nextId()));
        labelDao.save(label);
    }

    /**
     * @author: zhangyu
     * @date: 2019-12-16
     * @param: [label]
     * @return: void
     * 功能描述: 修改标签
     */

    public void update(Label label) {
       labelDao.save(label);
    }

    /**
     * @author: zhangyu
     * @date: 2019-12-16
     * @param: [labelId]
     * @return: void
     * 功能描述: 通过标签id删除标签
     */

    public void deleteById(String labelId) {
        labelDao.deleteById(labelId);
    }


    /**
     * @author: zhangyu
     * @date: 2019-12-17
     * @param: [s]
     * @return: java.util.List<com.tensquare.base.pojo.Label>
     * 功能描述: 查询所有有效的标签
     */
    public List<Label> findByStatus(String s) {

        return labelDao.findAll();
    }

    /**
     * @author: zhangyu
     * @date: 2019-12-17
     * @param: [searchLabel]
     * @return: java.lang.Object
     * 功能描述: 条件查询标签
     */
    public List<Label> search(Label searchLabel) {
        Specification<Label> sepc = createSepcification(searchLabel);
        return labelDao.findAll(sepc);
    }

    private Specification<Label> createSepcification(Label searchLabel) {
        return new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //定义存放查询条件的数组
                List<Predicate> predicateList = new ArrayList<>();
                if (searchLabel != null){
                    //设置标签名的条件查询
                    if (StringUtils.isNotBlank(searchLabel.getLabelname())){
                        predicateList.add(criteriaBuilder.like(root.get("labelname").as(String.class),"%"+searchLabel.getLabelname()+"%"));
                    }
                    //设置是否推荐条件查询
                    if (StringUtils.isNotBlank(searchLabel.getRecommend())){
                        predicateList.add(criteriaBuilder.equal(root.get("recommend").as(String.class),searchLabel.getRecommend()));
                    }
                    //设置是否有效条件查询
                    if (StringUtils.isNotBlank(searchLabel.getState())){
                        predicateList.add(criteriaBuilder.equal(root.get("state").as(String.class),searchLabel.getState()));
                    }
                    Predicate[] predicates = predicateList.toArray(new Predicate[predicateList.size()]);
                    return criteriaBuilder.and(predicates);
                }
                return null;
            }
        };
    }


    /**
     * @author: zhangyu
     * @date: 2019-12-17
     * @param: [page, size, searchLabel]
     * @return: entity.PageResult<com.tensquare.base.pojo.Label>
     * 功能描述: 条件查询带分页
     */
    public PageResult<Label> search(int page, int size, Label searchLabel) {
        Page pages = labelDao.findAll(createSepcification(searchLabel), PageRequest.of(page - 1, size));
        return new PageResult<>(pages.getTotalElements(),pages.getContent());
    }
}
