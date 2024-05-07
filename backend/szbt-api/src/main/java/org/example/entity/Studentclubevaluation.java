package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 
 * @TableName studentclubevaluation
 */
@TableName(value ="studentclubevaluation")
public class Studentclubevaluation implements Serializable {
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
    private Integer clubId;

    /**
     * 
     */
    private String handoverMethod;

    /**
     * 
     */
    private Integer handoverParticipantsCount;

    /**
     * 
     */
    private Integer advisorParticipation;

    /**
     * 
     */
    private Object publicityManagementEffectiveness;

    /**
     * 
     */
    private Object hostedSchoolLevelActivities;

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
    public String getHandoverMethod() {
        return handoverMethod;
    }

    /**
     * 
     */
    public void setHandoverMethod(String handoverMethod) {
        this.handoverMethod = handoverMethod;
    }

    /**
     * 
     */
    public Integer getHandoverParticipantsCount() {
        return handoverParticipantsCount;
    }

    /**
     * 
     */
    public void setHandoverParticipantsCount(Integer handoverParticipantsCount) {
        this.handoverParticipantsCount = handoverParticipantsCount;
    }

    /**
     * 
     */
    public Integer getAdvisorParticipation() {
        return advisorParticipation;
    }

    /**
     * 
     */
    public void setAdvisorParticipation(Integer advisorParticipation) {
        this.advisorParticipation = advisorParticipation;
    }

    /**
     * 
     */
    public Object getPublicityManagementEffectiveness() {
        return publicityManagementEffectiveness;
    }

    /**
     * 
     */
    public void setPublicityManagementEffectiveness(Object publicityManagementEffectiveness) {
        this.publicityManagementEffectiveness = publicityManagementEffectiveness;
    }

    /**
     * 
     */
    public Object getHostedSchoolLevelActivities() {
        return hostedSchoolLevelActivities;
    }

    /**
     * 
     */
    public void setHostedSchoolLevelActivities(Object hostedSchoolLevelActivities) {
        this.hostedSchoolLevelActivities = hostedSchoolLevelActivities;
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
        Studentclubevaluation other = (Studentclubevaluation) that;
        return (this.getRecordId() == null ? other.getRecordId() == null : this.getRecordId().equals(other.getRecordId()))
            && (this.getDeclarationYear() == null ? other.getDeclarationYear() == null : this.getDeclarationYear().equals(other.getDeclarationYear()))
            && (this.getClubId() == null ? other.getClubId() == null : this.getClubId().equals(other.getClubId()))
            && (this.getHandoverMethod() == null ? other.getHandoverMethod() == null : this.getHandoverMethod().equals(other.getHandoverMethod()))
            && (this.getHandoverParticipantsCount() == null ? other.getHandoverParticipantsCount() == null : this.getHandoverParticipantsCount().equals(other.getHandoverParticipantsCount()))
            && (this.getAdvisorParticipation() == null ? other.getAdvisorParticipation() == null : this.getAdvisorParticipation().equals(other.getAdvisorParticipation()))
            && (this.getPublicityManagementEffectiveness() == null ? other.getPublicityManagementEffectiveness() == null : this.getPublicityManagementEffectiveness().equals(other.getPublicityManagementEffectiveness()))
            && (this.getHostedSchoolLevelActivities() == null ? other.getHostedSchoolLevelActivities() == null : this.getHostedSchoolLevelActivities().equals(other.getHostedSchoolLevelActivities()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRecordId() == null) ? 0 : getRecordId().hashCode());
        result = prime * result + ((getDeclarationYear() == null) ? 0 : getDeclarationYear().hashCode());
        result = prime * result + ((getClubId() == null) ? 0 : getClubId().hashCode());
        result = prime * result + ((getHandoverMethod() == null) ? 0 : getHandoverMethod().hashCode());
        result = prime * result + ((getHandoverParticipantsCount() == null) ? 0 : getHandoverParticipantsCount().hashCode());
        result = prime * result + ((getAdvisorParticipation() == null) ? 0 : getAdvisorParticipation().hashCode());
        result = prime * result + ((getPublicityManagementEffectiveness() == null) ? 0 : getPublicityManagementEffectiveness().hashCode());
        result = prime * result + ((getHostedSchoolLevelActivities() == null) ? 0 : getHostedSchoolLevelActivities().hashCode());
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
        sb.append(", clubId=").append(clubId);
        sb.append(", handoverMethod=").append(handoverMethod);
        sb.append(", handoverParticipantsCount=").append(handoverParticipantsCount);
        sb.append(", advisorParticipation=").append(advisorParticipation);
        sb.append(", publicityManagementEffectiveness=").append(publicityManagementEffectiveness);
        sb.append(", hostedSchoolLevelActivities=").append(hostedSchoolLevelActivities);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}