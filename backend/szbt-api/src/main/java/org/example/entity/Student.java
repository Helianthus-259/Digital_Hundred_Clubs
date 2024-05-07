package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName student
 */
@TableName(value ="student")
public class Student implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer studentId;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String stName;

    /**
     * 
     */
    private String studentNumber;

    /**
     * 
     */
    private Integer gender;

    /**
     * 
     */
    private String college;

    /**
     * 
     */
    private Integer grade;

    /**
     * 
     */
    private String contact;

    /**
     * 
     */
    private String pwd;

    /**
     * 
     */
    private String politicalStatus;

    /**
     * 
     */
    private Date dateOfBirth;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getStudentId() {
        return studentId;
    }

    /**
     * 
     */
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    /**
     * 
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     */
    public String getStName() {
        return stName;
    }

    /**
     * 
     */
    public void setStName(String stName) {
        this.stName = stName;
    }

    /**
     * 
     */
    public String getStudentNumber() {
        return studentNumber;
    }

    /**
     * 
     */
    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    /**
     * 
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * 
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * 
     */
    public String getCollege() {
        return college;
    }

    /**
     * 
     */
    public void setCollege(String college) {
        this.college = college;
    }

    /**
     * 
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * 
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    /**
     * 
     */
    public String getContact() {
        return contact;
    }

    /**
     * 
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * 
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * 
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * 
     */
    public String getPoliticalStatus() {
        return politicalStatus;
    }

    /**
     * 
     */
    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    /**
     * 
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * 
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Student other = (Student) that;
        return (this.getStudentId() == null ? other.getStudentId() == null : this.getStudentId().equals(other.getStudentId()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getStName() == null ? other.getStName() == null : this.getStName().equals(other.getStName()))
            && (this.getStudentNumber() == null ? other.getStudentNumber() == null : this.getStudentNumber().equals(other.getStudentNumber()))
            && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
            && (this.getCollege() == null ? other.getCollege() == null : this.getCollege().equals(other.getCollege()))
            && (this.getGrade() == null ? other.getGrade() == null : this.getGrade().equals(other.getGrade()))
            && (this.getContact() == null ? other.getContact() == null : this.getContact().equals(other.getContact()))
            && (this.getPwd() == null ? other.getPwd() == null : this.getPwd().equals(other.getPwd()))
            && (this.getPoliticalStatus() == null ? other.getPoliticalStatus() == null : this.getPoliticalStatus().equals(other.getPoliticalStatus()))
            && (this.getDateOfBirth() == null ? other.getDateOfBirth() == null : this.getDateOfBirth().equals(other.getDateOfBirth()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getStName() == null) ? 0 : getStName().hashCode());
        result = prime * result + ((getStudentNumber() == null) ? 0 : getStudentNumber().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getCollege() == null) ? 0 : getCollege().hashCode());
        result = prime * result + ((getGrade() == null) ? 0 : getGrade().hashCode());
        result = prime * result + ((getContact() == null) ? 0 : getContact().hashCode());
        result = prime * result + ((getPwd() == null) ? 0 : getPwd().hashCode());
        result = prime * result + ((getPoliticalStatus() == null) ? 0 : getPoliticalStatus().hashCode());
        result = prime * result + ((getDateOfBirth() == null) ? 0 : getDateOfBirth().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", studentId=").append(studentId);
        sb.append(", email=").append(email);
        sb.append(", stName=").append(stName);
        sb.append(", studentNumber=").append(studentNumber);
        sb.append(", gender=").append(gender);
        sb.append(", college=").append(college);
        sb.append(", grade=").append(grade);
        sb.append(", contact=").append(contact);
        sb.append(", pwd=").append(pwd);
        sb.append(", politicalStatus=").append(politicalStatus);
        sb.append(", dateOfBirth=").append(dateOfBirth);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}