package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName activity
 */
@TableName(value ="activity")
public class Activity implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer activityId;

    /**
     * 
     */
    private Integer clubId;

    /**
     * 
     */
    private String activityName;

    /**
     * 
     */
    private Date activityTime;

    /**
     * 
     */
    private String activityLocation;

    /**
     * 
     */
    private String applicationFormAttachment;

    /**
     * 
     */
    private Integer activityStatus;

    /**
     * 
     */
    private String activityEffect;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getActivityId() {
        return activityId;
    }

    /**
     * 
     */
    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
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
    public String getActivityName() {
        return activityName;
    }

    /**
     * 
     */
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    /**
     * 
     */
    public Date getActivityTime() {
        return activityTime;
    }

    /**
     * 
     */
    public void setActivityTime(Date activityTime) {
        this.activityTime = activityTime;
    }

    /**
     * 
     */
    public String getActivityLocation() {
        return activityLocation;
    }

    /**
     * 
     */
    public void setActivityLocation(String activityLocation) {
        this.activityLocation = activityLocation;
    }

    /**
     * 
     */
    public String getApplicationFormAttachment() {
        return applicationFormAttachment;
    }

    /**
     * 
     */
    public void setApplicationFormAttachment(String applicationFormAttachment) {
        this.applicationFormAttachment = applicationFormAttachment;
    }

    /**
     * 
     */
    public Integer getActivityStatus() {
        return activityStatus;
    }

    /**
     * 
     */
    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    /**
     * 
     */
    public String getActivityEffect() {
        return activityEffect;
    }

    /**
     * 
     */
    public void setActivityEffect(String activityEffect) {
        this.activityEffect = activityEffect;
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
        Activity other = (Activity) that;
        return (this.getActivityId() == null ? other.getActivityId() == null : this.getActivityId().equals(other.getActivityId()))
            && (this.getClubId() == null ? other.getClubId() == null : this.getClubId().equals(other.getClubId()))
            && (this.getActivityName() == null ? other.getActivityName() == null : this.getActivityName().equals(other.getActivityName()))
            && (this.getActivityTime() == null ? other.getActivityTime() == null : this.getActivityTime().equals(other.getActivityTime()))
            && (this.getActivityLocation() == null ? other.getActivityLocation() == null : this.getActivityLocation().equals(other.getActivityLocation()))
            && (this.getApplicationFormAttachment() == null ? other.getApplicationFormAttachment() == null : this.getApplicationFormAttachment().equals(other.getApplicationFormAttachment()))
            && (this.getActivityStatus() == null ? other.getActivityStatus() == null : this.getActivityStatus().equals(other.getActivityStatus()))
            && (this.getActivityEffect() == null ? other.getActivityEffect() == null : this.getActivityEffect().equals(other.getActivityEffect()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getActivityId() == null) ? 0 : getActivityId().hashCode());
        result = prime * result + ((getClubId() == null) ? 0 : getClubId().hashCode());
        result = prime * result + ((getActivityName() == null) ? 0 : getActivityName().hashCode());
        result = prime * result + ((getActivityTime() == null) ? 0 : getActivityTime().hashCode());
        result = prime * result + ((getActivityLocation() == null) ? 0 : getActivityLocation().hashCode());
        result = prime * result + ((getApplicationFormAttachment() == null) ? 0 : getApplicationFormAttachment().hashCode());
        result = prime * result + ((getActivityStatus() == null) ? 0 : getActivityStatus().hashCode());
        result = prime * result + ((getActivityEffect() == null) ? 0 : getActivityEffect().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", activityId=").append(activityId);
        sb.append(", clubId=").append(clubId);
        sb.append(", activityName=").append(activityName);
        sb.append(", activityTime=").append(activityTime);
        sb.append(", activityLocation=").append(activityLocation);
        sb.append(", applicationFormAttachment=").append(applicationFormAttachment);
        sb.append(", activityStatus=").append(activityStatus);
        sb.append(", activityEffect=").append(activityEffect);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}