package com.glacierluo.platform.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Null;

@Entity
public class User {
    @Id
    private Long id;
    private String name;
    private String nickname;
    private String phoneNumber;
    private Long idNumber;
    private String institute;//学院
    private String major;
    private String classNumber;
    private String grade;
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
