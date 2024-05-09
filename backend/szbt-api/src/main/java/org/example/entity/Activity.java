package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName activity
 */
@TableName(value ="activity")
@Data
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
    private Date activityPublishTime;

    /**
     * 
     */
    private Date activityStartTime;

    /**
     * 
     */
    private Date activityEndtime;

    /**
     * 
     */
    private String activityLocation;

    /**
     * 
     */
    private String activityIntroduction;

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

    /**
     * 
     */
    private String imageUrl;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

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
            && (this.getActivityPublishTime() == null ? other.getActivityPublishTime() == null : this.getActivityPublishTime().equals(other.getActivityPublishTime()))
            && (this.getActivityStartTime() == null ? other.getActivityStartTime() == null : this.getActivityStartTime().equals(other.getActivityStartTime()))
            && (this.getActivityEndtime() == null ? other.getActivityEndtime() == null : this.getActivityEndtime().equals(other.getActivityEndtime()))
            && (this.getActivityLocation() == null ? other.getActivityLocation() == null : this.getActivityLocation().equals(other.getActivityLocation()))
            && (this.getActivityIntroduction() == null ? other.getActivityIntroduction() == null : this.getActivityIntroduction().equals(other.getActivityIntroduction()))
            && (this.getApplicationFormAttachment() == null ? other.getApplicationFormAttachment() == null : this.getApplicationFormAttachment().equals(other.getApplicationFormAttachment()))
            && (this.getActivityStatus() == null ? other.getActivityStatus() == null : this.getActivityStatus().equals(other.getActivityStatus()))
            && (this.getActivityEffect() == null ? other.getActivityEffect() == null : this.getActivityEffect().equals(other.getActivityEffect()))
            && (this.getImageUrl() == null ? other.getImageUrl() == null : this.getImageUrl().equals(other.getImageUrl()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getActivityId() == null) ? 0 : getActivityId().hashCode());
        result = prime * result + ((getClubId() == null) ? 0 : getClubId().hashCode());
        result = prime * result + ((getActivityName() == null) ? 0 : getActivityName().hashCode());
        result = prime * result + ((getActivityPublishTime() == null) ? 0 : getActivityPublishTime().hashCode());
        result = prime * result + ((getActivityStartTime() == null) ? 0 : getActivityStartTime().hashCode());
        result = prime * result + ((getActivityEndtime() == null) ? 0 : getActivityEndtime().hashCode());
        result = prime * result + ((getActivityLocation() == null) ? 0 : getActivityLocation().hashCode());
        result = prime * result + ((getActivityIntroduction() == null) ? 0 : getActivityIntroduction().hashCode());
        result = prime * result + ((getApplicationFormAttachment() == null) ? 0 : getApplicationFormAttachment().hashCode());
        result = prime * result + ((getActivityStatus() == null) ? 0 : getActivityStatus().hashCode());
        result = prime * result + ((getActivityEffect() == null) ? 0 : getActivityEffect().hashCode());
        result = prime * result + ((getImageUrl() == null) ? 0 : getImageUrl().hashCode());
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
        sb.append(", activityPublishTime=").append(activityPublishTime);
        sb.append(", activityStartTime=").append(activityStartTime);
        sb.append(", activityEndtime=").append(activityEndtime);
        sb.append(", activityLocation=").append(activityLocation);
        sb.append(", activityIntroduction=").append(activityIntroduction);
        sb.append(", applicationFormAttachment=").append(applicationFormAttachment);
        sb.append(", activityStatus=").append(activityStatus);
        sb.append(", activityEffect=").append(activityEffect);
        sb.append(", imageUrl=").append(imageUrl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}