package com.hy.mybatis.service;

import com.hy.mybatis.mapper.DeptDao;
import com.hy.mybatis.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeptService {

    @Autowired
    private DeptDao deptDao;

    /**
     * 查询所有部门
     * @return
     */
    public List<Dept> queryDept(){
        return (List<Dept>)deptDao.queryAll();
    }
}
