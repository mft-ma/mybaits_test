package com.hy.mybatis;

import com.hy.mybatis.pojo.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTest {

    public static void main(String[] args) throws IOException {

        InputStream in= Resources.getResourceAsStream("mybatis11.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        SqlSession session = factory.openSession(true);
        /*String method="com.hy.mybatis.dao.EmpDao"+"."+"queryAll";
        List<Employee> employeeList = session.selectList(method);
        employeeList.forEach(System.out::println);*/
        Employee employee=new Employee();
        employee.setId("11111111111");
        employee.setName("张44");
        employee.setGender("M");
        String method="com.hy.mybatis.dao.EmpDao"+"."+"queryById";
        Employee emp = session.selectOne(method, employee);
        //System.out.println("添加记录的行数：" + rows);
        System.out.println(emp);
        session.close();
    }
}
