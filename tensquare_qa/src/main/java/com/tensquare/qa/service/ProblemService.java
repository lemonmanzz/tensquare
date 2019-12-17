package com.tensquare.qa.service;

import com.tensquare.qa.dao.ProblemDao;
import com.tensquare.qa.pojo.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProblemService {
    @Autowired
    private ProblemDao problemDao;
    /**
     * @author: zhangyu
     * @date: 2019-12-17
     * @param: [labelid, page, size]
     * @return: java.util.List<com.tensquare.qa.pojo.Problem>
     * 功能描述: 获取最新问答列表
     */
    public List<Problem> getNewList(String labelid, int page, int size) {
        return problemDao.getNewList(labelid,PageRequest.of(page-1,size));
    }

    /**
     * @author: zhangyu
     * @date: 2019-12-17
     * @param: [labelid, page, size]
     * @return: java.util.List<com.tensquare.qa.pojo.Problem>
     * 功能描述: 获取热门问答列表
     */
    public List<Problem> getHotList(String labelid, int page, int size) {
        return problemDao.getHotList(labelid, PageRequest.of(page-1,size));
    }
}
