package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName clubmeeting
 */
@TableName(value ="clubmeeting")
public class Clubmeeting implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer meetingId;

    /**
     * 
     */
    private Integer clubId;

    /**
     * 
     */
    private Date meetingTime;

    /**
     * 
     */
    private String location;

    /**
     * 
     */
    private Integer category;

    /**
     * 
     */
    private String advisorName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getMeetingId() {
        return meetingId;
    }

    /**
     * 
     */
    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
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
    public Date getMeetingTime() {
        return meetingTime;
    }

    /**
     * 
     */
    public void setMeetingTime(Date meetingTime) {
        this.meetingTime = meetingTime;
    }

    /**
     * 
     */
    public String getLocation() {
        return location;
    }

    /**
     * 
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 
     */
    public Integer getCategory() {
        return category;
    }

    /**
     * 
     */
    public void setCategory(Integer category) {
        this.category = category;
    }

    /**
     * 
     */
    public String getAdvisorName() {
        return advisorName;
    }

    /**
     * 
     */
    public void setAdvisorName(String advisorName) {
        this.advisorName = advisorName;
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
        Clubmeeting other = (Clubmeeting) that;
        return (this.getMeetingId() == null ? other.getMeetingId() == null : this.getMeetingId().equals(other.getMeetingId()))
            && (this.getClubId() == null ? other.getClubId() == null : this.getClubId().equals(other.getClubId()))
            && (this.getMeetingTime() == null ? other.getMeetingTime() == null : this.getMeetingTime().equals(other.getMeetingTime()))
            && (this.getLocation() == null ? other.getLocation() == null : this.getLocation().equals(other.getLocation()))
            && (this.getCategory() == null ? other.getCategory() == null : this.getCategory().equals(other.getCategory()))
            && (this.getAdvisorName() == null ? other.getAdvisorName() == null : this.getAdvisorName().equals(other.getAdvisorName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMeetingId() == null) ? 0 : getMeetingId().hashCode());
        result = prime * result + ((getClubId() == null) ? 0 : getClubId().hashCode());
        result = prime * result + ((getMeetingTime() == null) ? 0 : getMeetingTime().hashCode());
        result = prime * result + ((getLocation() == null) ? 0 : getLocation().hashCode());
        result = prime * result + ((getCategory() == null) ? 0 : getCategory().hashCode());
        result = prime * result + ((getAdvisorName() == null) ? 0 : getAdvisorName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", meetingId=").append(meetingId);
        sb.append(", clubId=").append(clubId);
        sb.append(", meetingTime=").append(meetingTime);
        sb.append(", location=").append(location);
        sb.append(", category=").append(category);
        sb.append(", advisorName=").append(advisorName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}