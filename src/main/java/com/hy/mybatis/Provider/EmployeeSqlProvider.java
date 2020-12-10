package com.hy.mybatis.Provider;

import com.hy.mybatis.pojo.Employee;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

public class EmployeeSqlProvider {

    public String queryAll(Employee employee){
        StringBuffer sql = new StringBuffer("select * from employee where 1=1 ");
        if(employee.getName() != null&& employee.getName()!=""){
            sql.append(" and name like '%' #{name} '%' ");
        }
        if(employee.getGender() != null&& employee.getGender()!=""){
            sql.append(" and gender=#{gender} ");
        }
        if(employee.getDid() != null&& employee.getDid()!=0){
            sql.append(" and did=#{did} ");
        }

        /*select * from employee
        <where>
            <if test="name != null and name !=''">
                and name like "%" #{name} "%"
            </if>
            <if test="gender != null and gender !=''">
                and gender=#{gender}
            </if>
            <if test="did != null and did !=0 ">
                and did=#{did}
            </if>
        </where>*/

        return sql.toString();
    }

    public String insertBatch(List<Employee> list){
        StringBuffer sql = new StringBuffer("");
        sql.append("insert into employee(id,did,name,gender,birthday,image,salary,entry_date,resume) values");
        StringBuffer falg=new StringBuffer();
        /*SQL sql1=new SQL();
        sql1.INSERT_INTO("employee").INTO_COLUMNS("id","did","name","gender","birthday","image",
                "salary","entry_date","resume").INTO_VALUES("#{id}","#{did}","#{name}","#{gender}",
                "#{birthday}","#{image}","#{salary}","#{entry_date}","#{resume}");*/
        for (Employee emp:list){
            falg.append("("+emp.getId()+","+emp.getDid()+","+emp.getName()+","+emp.getGender()+","
                    +emp.getBirthday()+","+emp.getImage()+","+emp.getSalary()+","+emp.getEntryDate()
                    +","+emp.getResume()+","+")");
            if(list.size()-1!=0){
                falg.append(",");
            }
        }
        sql.append(falg);
        /*insert into employee(id,did,name,gender,birthday,image,salary,entry_date,resume) values
        <foreach collection="list" item="emp" separator=",">
            (#{emp.id},#{emp.did},#{emp.name},#{emp.gender},#{emp.birthday},#{emp.image},#{emp.salary},
            #{emp.entry_date},#{emp.resume})
        </foreach>*/
        return sql.toString();
    }

    public String deleteBatch(String[] ids){
        System.out.println("provider的deleteBatch的id数组====" + ids);
        StringBuffer sql=new StringBuffer("delete from employee where 1=1 and id in");
        sql.append("(");
        for (int i=0;i<ids.length;i++){
            sql.append(ids[i]);
            if(ids.length-1!=0){
                sql.append(",");
            }
        }
        sql.append(")");
        System.out.println("deleteBatch的sql语句" + sql.toString());
        return sql.toString();
    }
}
