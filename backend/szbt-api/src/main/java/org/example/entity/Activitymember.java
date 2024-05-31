package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName activitymember
 */
@TableName(value ="activitymember")
@Data
public class Activitymember implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer activityMemberId;

    /**
     *
     */
    private Integer studentId;

    /**
     *
     */
    private String activityName;

    /**
     *
     */
    private Integer activityId;

    /**
     *
     */
    private String personalEffect;

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
        Activitymember other = (Activitymember) that;
        return (this.getActivityMemberId() == null ? other.getActivityMemberId() == null : this.getActivityMemberId().equals(other.getActivityMemberId()))
                && (this.getStudentId() == null ? other.getStudentId() == null : this.getStudentId().equals(other.getStudentId()))
                && (this.getActivityName() == null ? other.getActivityName() == null : this.getActivityName().equals(other.getActivityName()))
                && (this.getActivityId() == null ? other.getActivityId() == null : this.getActivityId().equals(other.getActivityId()))
                && (this.getPersonalEffect() == null ? other.getPersonalEffect() == null : this.getPersonalEffect().equals(other.getPersonalEffect()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getActivityMemberId() == null) ? 0 : getActivityMemberId().hashCode());
        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());
        result = prime * result + ((getActivityName() == null) ? 0 : getActivityName().hashCode());
        result = prime * result + ((getActivityId() == null) ? 0 : getActivityId().hashCode());
        result = prime * result + ((getPersonalEffect() == null) ? 0 : getPersonalEffect().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", activityMemberId=").append(activityMemberId);
        sb.append(", studentId=").append(studentId);
        sb.append(", activityName=").append(activityName);
        sb.append(", activityId=").append(activityId);
        sb.append(", personalEffect=").append(personalEffect);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}