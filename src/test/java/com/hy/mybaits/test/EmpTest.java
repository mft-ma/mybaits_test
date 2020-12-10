package com.hy.mybaits.test;

import com.github.pagehelper.PageHelper;
import com.hy.mybatis.pojo.Employee;
import com.hy.mybatis.service.EmpService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;

public class EmpTest {

    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring.xml");
        EmpService empService = applicationContext.getBean(EmpService.class);
        Employee employee=new Employee();
        employee.setId("11111111112");
        employee.setName("张");
        employee.setGender("F");
        PageHelper.startPage(2,5);
        List<Employee> employees = empService.queryAll(employee,1);
        employees.forEach(System.out::println);
    }

}
