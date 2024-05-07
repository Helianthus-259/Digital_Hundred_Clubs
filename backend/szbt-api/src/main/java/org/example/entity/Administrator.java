package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 
 * @TableName administrator
 */
@TableName(value ="administrator")
public class Administrator implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer adminId;

    /**
     * 
     */
    private String adName;

    /**
     * 
     */
    private Integer authority;

    /**
     * 
     */
    private String pwd;

    /**
     * 
     */
    private String contact;

    /**
     * 
     */
    private String departmentName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getAdminId() {
        return adminId;
    }

    /**
     * 
     */
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    /**
     * 
     */
    public String getAdName() {
        return adName;
    }

    /**
     * 
     */
    public void setAdName(String adName) {
        this.adName = adName;
    }

    /**
     * 
     */
    public Integer getAuthority() {
        return authority;
    }

    /**
     * 
     */
    public void setAuthority(Integer authority) {
        this.authority = authority;
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
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * 
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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
        Administrator other = (Administrator) that;
        return (this.getAdminId() == null ? other.getAdminId() == null : this.getAdminId().equals(other.getAdminId()))
            && (this.getAdName() == null ? other.getAdName() == null : this.getAdName().equals(other.getAdName()))
            && (this.getAuthority() == null ? other.getAuthority() == null : this.getAuthority().equals(other.getAuthority()))
            && (this.getPwd() == null ? other.getPwd() == null : this.getPwd().equals(other.getPwd()))
            && (this.getContact() == null ? other.getContact() == null : this.getContact().equals(other.getContact()))
            && (this.getDepartmentName() == null ? other.getDepartmentName() == null : this.getDepartmentName().equals(other.getDepartmentName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAdminId() == null) ? 0 : getAdminId().hashCode());
        result = prime * result + ((getAdName() == null) ? 0 : getAdName().hashCode());
        result = prime * result + ((getAuthority() == null) ? 0 : getAuthority().hashCode());
        result = prime * result + ((getPwd() == null) ? 0 : getPwd().hashCode());
        result = prime * result + ((getContact() == null) ? 0 : getContact().hashCode());
        result = prime * result + ((getDepartmentName() == null) ? 0 : getDepartmentName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", adminId=").append(adminId);
        sb.append(", adName=").append(adName);
        sb.append(", authority=").append(authority);
        sb.append(", pwd=").append(pwd);
        sb.append(", contact=").append(contact);
        sb.append(", departmentName=").append(departmentName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}