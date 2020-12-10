package com.hy.mybatis.pojo;

import java.io.Serializable;
import java.sql.Date;

public class Employee implements Serializable {
    private String id;
    private Integer did;
    private String name;
    private String gender;
    private Date birthday;
    private String image;//图片
    private Float salary;
    private Date entryDate;
    private String resume;

    private Dept dept;

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", did=" + did +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", image='" + image + '\'' +
                ", salary=" + salary +
                ", entryDate=" + entryDate +
                ", resume='" + resume + '\'' +
                ", dept=" + dept +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }
}
