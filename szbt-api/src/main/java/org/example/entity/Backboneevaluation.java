package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 
 * @TableName backboneevaluation
 */
@TableName(value ="backboneevaluation")
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
    private Integer tenurePeriod;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getRecordId() {
        return recordId;
    }

    /**
     * 
     */
    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    /**
     * 
     */
    public Integer getDeclarationYear() {
        return declarationYear;
    }

    /**
     * 
     */
    public void setDeclarationYear(Integer declarationYear) {
        this.declarationYear = declarationYear;
    }

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
    public Integer getTenurePeriod() {
        return tenurePeriod;
    }

    /**
     * 
     */
    public void setTenurePeriod(Integer tenurePeriod) {
        this.tenurePeriod = tenurePeriod;
    }

    /**
     * 
     */
    public Object getAchievements() {
        return achievements;
    }

    /**
     * 
     */
    public void setAchievements(Object achievements) {
        this.achievements = achievements;
    }

    /**
     * 
     */
    public Object getTrainingParticipation() {
        return trainingParticipation;
    }

    /**
     * 
     */
    public void setTrainingParticipation(Object trainingParticipation) {
        this.trainingParticipation = trainingParticipation;
    }

    /**
     * 
     */
    public Object getAwards() {
        return awards;
    }

    /**
     * 
     */
    public void setAwards(Object awards) {
        this.awards = awards;
    }

    /**
     * 
     */
    public String getClubWorkStatus() {
        return clubWorkStatus;
    }

    /**
     * 
     */
    public void setClubWorkStatus(String clubWorkStatus) {
        this.clubWorkStatus = clubWorkStatus;
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
        Backboneevaluation other = (Backboneevaluation) that;
        return (this.getRecordId() == null ? other.getRecordId() == null : this.getRecordId().equals(other.getRecordId()))
            && (this.getDeclarationYear() == null ? other.getDeclarationYear() == null : this.getDeclarationYear().equals(other.getDeclarationYear()))
            && (this.getStudentId() == null ? other.getStudentId() == null : this.getStudentId().equals(other.getStudentId()))
            && (this.getClubId() == null ? other.getClubId() == null : this.getClubId().equals(other.getClubId()))
            && (this.getTenurePeriod() == null ? other.getTenurePeriod() == null : this.getTenurePeriod().equals(other.getTenurePeriod()))
            && (this.getAchievements() == null ? other.getAchievements() == null : this.getAchievements().equals(other.getAchievements()))
            && (this.getTrainingParticipation() == null ? other.getTrainingParticipation() == null : this.getTrainingParticipation().equals(other.getTrainingParticipation()))
            && (this.getAwards() == null ? other.getAwards() == null : this.getAwards().equals(other.getAwards()))
            && (this.getClubWorkStatus() == null ? other.getClubWorkStatus() == null : this.getClubWorkStatus().equals(other.getClubWorkStatus()));
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
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}