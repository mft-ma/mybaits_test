package com.hy.mybatis.mapper;

import com.hy.mybatis.Provider.DeptSqlProvider;
import com.hy.mybatis.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface DeptDao {

    @Select("select id,name from dept")
    List<Dept> queryAll();

    @SelectProvider(type = DeptSqlProvider.class,method = "queryById")
    Dept queryById(Integer did);
}
