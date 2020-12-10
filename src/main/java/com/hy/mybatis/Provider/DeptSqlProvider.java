package com.hy.mybatis.Provider;

import com.hy.mybatis.pojo.Dept;
import org.apache.ibatis.jdbc.SQL;

public class DeptSqlProvider {

    public String queryById(Integer did){
        String sql =null;
        if(did != null && did!=0){
            sql="select id,name from dept where id=#{value} ";
        }
        return sql;
    }
}
