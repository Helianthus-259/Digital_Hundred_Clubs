package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName clubawards
 */
@TableName(value ="clubawards")
public class Clubawards implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer awardId;

    /**
     * 
     */
    private Integer clubId;

    /**
     * 
     */
    private String awardName;

    /**
     * 
     */
    private Date awardTime;

    /**
     * 
     */
    private String issuingAuthority;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getAwardId() {
        return awardId;
    }

    /**
     * 
     */
    public void setAwardId(Integer awardId) {
        this.awardId = awardId;
    }

    /**
     * 
     */
    public Integer getClubId() {
        return clubId;
    }

    /**
     * 
     */
    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

    /**
     * 
     */
    public String getAwardName() {
        return awardName;
    }

    /**
     * 
     */
    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    /**
     * 
     */
    public Date getAwardTime() {
        return awardTime;
    }

    /**
     * 
     */
    public void setAwardTime(Date awardTime) {
        this.awardTime = awardTime;
    }

    /**
     * 
     */
    public String getIssuingAuthority() {
        return issuingAuthority;
    }

    /**
     * 
     */
    public void setIssuingAuthority(String issuingAuthority) {
        this.issuingAuthority = issuingAuthority;
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
        Clubawards other = (Clubawards) that;
        return (this.getAwardId() == null ? other.getAwardId() == null : this.getAwardId().equals(other.getAwardId()))
            && (this.getClubId() == null ? other.getClubId() == null : this.getClubId().equals(other.getClubId()))
            && (this.getAwardName() == null ? other.getAwardName() == null : this.getAwardName().equals(other.getAwardName()))
            && (this.getAwardTime() == null ? other.getAwardTime() == null : this.getAwardTime().equals(other.getAwardTime()))
            && (this.getIssuingAuthority() == null ? other.getIssuingAuthority() == null : this.getIssuingAuthority().equals(other.getIssuingAuthority()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAwardId() == null) ? 0 : getAwardId().hashCode());
        result = prime * result + ((getClubId() == null) ? 0 : getClubId().hashCode());
        result = prime * result + ((getAwardName() == null) ? 0 : getAwardName().hashCode());
        result = prime * result + ((getAwardTime() == null) ? 0 : getAwardTime().hashCode());
        result = prime * result + ((getIssuingAuthority() == null) ? 0 : getIssuingAuthority().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", awardId=").append(awardId);
        sb.append(", clubId=").append(clubId);
        sb.append(", awardName=").append(awardName);
        sb.append(", awardTime=").append(awardTime);
        sb.append(", issuingAuthority=").append(issuingAuthority);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}