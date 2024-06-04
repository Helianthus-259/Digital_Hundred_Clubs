package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName backboneevaluation
 */
@TableName(value ="backboneevaluation")
@Data
public class Backboneevaluation implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer recordId;

    /**
     *
     */
    private Integer declarationYear;

    /**
     *
     */
    private Integer studentId;

    /**
     *
     */
    private Integer clubId;

    /**
     *
     */
    private String tenurePeriod;

    /**
     *
     */
    private Object achievements;

    /**
     *
     */
    private Object trainingParticipation;

    /**
     *
     */
    private Object awards;

    /**
     *
     */
    private String clubWorkStatus;

    /**
     *
     */
    private Integer status;

    /**
     *
     */
    private String reviewOpinion;

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
        Backboneevaluation other = (Backboneevaluation) that;
        return (this.getRecordId() == null ? other.getRecordId() == null : this.getRecordId().equals(other.getRecordId()))
                && (this.getDeclarationYear() == null ? other.getDeclarationYear() == null : this.getDeclarationYear().equals(other.getDeclarationYear()))
                && (this.getStudentId() == null ? other.getStudentId() == null : this.getStudentId().equals(other.getStudentId()))
                && (this.getClubId() == null ? other.getClubId() == null : this.getClubId().equals(other.getClubId()))
                && (this.getTenurePeriod() == null ? other.getTenurePeriod() == null : this.getTenurePeriod().equals(other.getTenurePeriod()))
                && (this.getAchievements() == null ? other.getAchievements() == null : this.getAchievements().equals(other.getAchievements()))
                && (this.getTrainingParticipation() == null ? other.getTrainingParticipation() == null : this.getTrainingParticipation().equals(other.getTrainingParticipation()))
                && (this.getAwards() == null ? other.getAwards() == null : this.getAwards().equals(other.getAwards()))
                && (this.getClubWorkStatus() == null ? other.getClubWorkStatus() == null : this.getClubWorkStatus().equals(other.getClubWorkStatus()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getReviewOpinion() == null ? other.getReviewOpinion() == null : this.getReviewOpinion().equals(other.getReviewOpinion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRecordId() == null) ? 0 : getRecordId().hashCode());
        result = prime * result + ((getDeclarationYear() == null) ? 0 : getDeclarationYear().hashCode());
        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());
        result = prime * result + ((getClubId() == null) ? 0 : getClubId().hashCode());
        result = prime * result + ((getTenurePeriod() == null) ? 0 : getTenurePeriod().hashCode());
        result = prime * result + ((getAchievements() == null) ? 0 : getAchievements().hashCode());
        result = prime * result + ((getTrainingParticipation() == null) ? 0 : getTrainingParticipation().hashCode());
        result = prime * result + ((getAwards() == null) ? 0 : getAwards().hashCode());
        result = prime * result + ((getClubWorkStatus() == null) ? 0 : getClubWorkStatus().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getReviewOpinion() == null) ? 0 : getReviewOpinion().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", recordId=").append(recordId);
        sb.append(", declarationYear=").append(declarationYear);
        sb.append(", studentId=").append(studentId);
        sb.append(", clubId=").append(clubId);
        sb.append(", tenurePeriod=").append(tenurePeriod);
        sb.append(", achievements=").append(achievements);
        sb.append(", trainingParticipation=").append(trainingParticipation);
        sb.append(", awards=").append(awards);
        sb.append(", clubWorkStatus=").append(clubWorkStatus);
        sb.append(", status=").append(status);
        sb.append(", reviewOpinion=").append(reviewOpinion);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}