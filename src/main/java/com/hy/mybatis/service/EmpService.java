package com.hy.mybatis.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hy.mybatis.mapper.EmpDao;
import com.hy.mybatis.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class EmpService {

    @Autowired
    private EmpDao empDao;

    /**
     * 查询所有
     * @param employee
     * @return
     * @throws IOException
     */
    public List<Employee> queryAll(Employee employee,Integer page) throws IOException {


        List<Employee> employeeList = empDao.queryAll(employee);


        return employeeList;
    }

    /**
     * 添加
     * @param employee
     * @return
     * @throws IOException
     */
    public int insert(Employee employee) throws IOException {
        int rows = empDao.insert(employee);
        System.out.println("添加记录的行数：" + rows);
        return rows;
    }

    /**
     * 修改
     * @param employee
     * @return
     * @throws IOException
     */
    public int update(Employee employee) throws IOException {
        int rows= empDao.update(employee);
        System.out.println("修改记录的行数：" + rows);
        return rows;
    }

    /**
     * 删除
     * @param employee
     * @return
     * @throws IOException
     */
    public int delete(Employee employee) throws IOException {
        int rows= empDao.delete(employee);
        System.out.println("删除记录的行数：" + rows);
        return rows;
    }

    /**
     * 根据id查询
     * @return
     * @throws IOException
     */
    public Employee queryById(String id) throws IOException {
        Employee employee=new Employee();
        employee.setId(id);
        Employee emp= empDao.queryById(employee);
        System.out.println(emp);
        return emp;
    }

    /**
     * 查询name是否存在
     * @param name
     * @return
     */
    public boolean queryName(String name){
        String id = empDao.queryByName(name);
        System.out.println("是否有此name====="+id);
        return id!=null;
    }

    /**
     * 批量删除
     * @param ids
     */
    public void deleteBatch(String[] ids){
        empDao.deleteBatch(ids);
    }

    /**
     * 批量添加
     * @param list
     */
    public void insertBatch(List<Employee> list){
        empDao.insertBatch(list);
    }


}
