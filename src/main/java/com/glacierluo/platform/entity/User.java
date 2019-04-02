package com.glacierluo.platform.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
//import com.alibaba.excel.annotation.ExcelProperty;
//import com.alibaba.excel.metadata.BaseRowModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@Entity
@ExcelTarget("User")
public class User{
    @Id
//    @ExcelProperty(value = "学号", index = 0)
    @Excel(name = "学号", orderNum = "1")
    private Long id;
//    @ExcelProperty(value = "姓名", index = 1)
    @Excel(name = "姓名", orderNum = "2")
    private String name;
//    @ExcelProperty(value = "昵称", index = 2)
    @Excel(name = "昵称", orderNum = "3")
    private String nickname;
//    @ExcelProperty(value = "手机号", index = 3)
    @Excel(name = "手机号", orderNum = "4")
    private String phoneNumber;
//    @ExcelProperty(value = "身份证号", index = 4)
    @Excel(name = "身份证号", orderNum = "5")
    private Long idNumber;
//    @ExcelProperty(value = "学院", index = 5)
    @Excel(name = "学院", orderNum = "6")
    private String institute;//学院
//    @ExcelProperty(value = "专业", index = 6)
    @Excel(name = "专业", orderNum = "7")
    private String major;
//    @ExcelProperty(value = "班级", index = 7)
    @Excel(name = "班级", orderNum = "8")
    private String classNumber;
//    @ExcelProperty(value = "年级", index = 8)
    @Excel(name = "年级", orderNum = "9")
    private String grade;
//    @ExcelProperty(value = "身份", index = 9)
    @Excel(name = "身份", orderNum = "10")
    private String identity;

    public User updateUser(User U){
        setName(U.name);
        setNickname(U.nickname);
        setPhoneNumber(U.phoneNumber);
        setIdNumber(U.idNumber);
        setInstitute(U.institute);
        setMajor(U.major);
        setClassNumber(U.classNumber);
        setGrade(U.grade);
        setIdentity(U.identity);
        return this;
    }

    public boolean checkNull(){
        return this.getId() != null && this.getName() != null && this.getNickname() != null && this.getPhoneNumber() != null
                && this.getIdNumber() != null && this.getInstitute() != null && this.getMajor() != null
                && this.getClassNumber() != null && this.getGrade() != null && this.getIdentity() != null;
    }

    public List<String> toListString(){
        List<String> temp = new ArrayList<>();
        temp.add(this.getId().toString());
        temp.add(this.getName());
        temp.add(this.getNickname());
        temp.add(this.getPhoneNumber());
        temp.add(this.getIdNumber().toString());
        temp.add(this.getInstitute());
        temp.add(this.getMajor());
        temp.add(this.getClassNumber());
        temp.add(this.getGrade());
        temp.add(this.getIdentity());
        return temp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Long idNumber) {
        this.idNumber = idNumber;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
