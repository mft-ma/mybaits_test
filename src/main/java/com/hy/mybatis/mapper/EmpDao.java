package com.hy.mybatis.mapper;

import com.hy.mybatis.Provider.EmployeeSqlProvider;
import com.hy.mybatis.pojo.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmpDao {

    @Results({@Result(property = "id",column = "id"),
              @Result(property = "did",column = "did"),
              @Result(property = "dept",column = "did",
                      one = @One(select = "com.hy.mybatis.mapper.DeptDao.queryById"))
    })
    @SelectProvider(type = EmployeeSqlProvider.class,method = "queryAll")
    List<Employee> queryAll(Employee employee);

    @SelectKey(statement = "select uuid()",keyProperty = "id",before = true,resultType = String.class)
    @Insert("insert into employee(id,did,name,gender,birthday,image,salary,entry_date,resume) " +
            "values(#{id},#{did},#{name},#{gender},#{birthday},#{image},#{salary},#{entryDate},#{resume})")
    int insert(Employee employee);

    /*@InsertProvider(type = EmployeeSqlProvider.class,method = "deleteBatch")*/
    int insertBatch(List<Employee> list);

    @Update("update employee set did=#{did},name=#{name},gender=#{gender},birthday=#{birthday}," +
            "image=#{image},salary=#{salary},entry_date=#{entryDate},resume=#{resume} " +
            "where id=#{id}")
    int update(Employee employee);

    @Delete("delete from employee where id=#{id}")
    int delete(Employee employee);

    /*@DeleteProvider(type = EmployeeSqlProvider.class,method = "deleteBatch")*/
    int deleteBatch(String[] ids);

    @Select("select id,did,name,gender,birthday,image,salary,entry_date,resume " +
            "from employee where id=#{id}")
    Employee queryById(Employee employee);

    @Select("select id from employee where name=#{value}")
    String queryByName(String name);


}
