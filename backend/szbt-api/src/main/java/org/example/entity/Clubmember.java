package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName clubmember
 */
@TableName(value ="clubmember")
@Data
public class Clubmember implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer clubMemberId;

    /**
     *
     */
    private Integer studentId;

    /**
     *
     */
    private String clubName;

    /**
     *
     */
    private Integer clubId;

    /**
     *
     */
    private Integer position;

    /**
     *
     */
    private Date joinDate;

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
        Clubmember other = (Clubmember) that;
        return (this.getClubMemberId() == null ? other.getClubMemberId() == null : this.getClubMemberId().equals(other.getClubMemberId()))
                && (this.getStudentId() == null ? other.getStudentId() == null : this.getStudentId().equals(other.getStudentId()))
                && (this.getClubName() == null ? other.getClubName() == null : this.getClubName().equals(other.getClubName()))
                && (this.getClubId() == null ? other.getClubId() == null : this.getClubId().equals(other.getClubId()))
                && (this.getPosition() == null ? other.getPosition() == null : this.getPosition().equals(other.getPosition()))
                && (this.getJoinDate() == null ? other.getJoinDate() == null : this.getJoinDate().equals(other.getJoinDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getClubMemberId() == null) ? 0 : getClubMemberId().hashCode());
        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());
        result = prime * result + ((getClubName() == null) ? 0 : getClubName().hashCode());
        result = prime * result + ((getClubId() == null) ? 0 : getClubId().hashCode());
        result = prime * result + ((getPosition() == null) ? 0 : getPosition().hashCode());
        result = prime * result + ((getJoinDate() == null) ? 0 : getJoinDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", clubMemberId=").append(clubMemberId);
        sb.append(", studentId=").append(studentId);
        sb.append(", clubName=").append(clubName);
        sb.append(", clubId=").append(clubId);
        sb.append(", position=").append(position);
        sb.append(", joinDate=").append(joinDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}