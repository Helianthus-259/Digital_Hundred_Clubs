package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName studentclubevaluation
 */
@TableName(value ="studentclubevaluation")
@Data
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
    private Object meetings;

    /**
     *
     */
    private Object associationAwards;

    /**
     *
     */
    private Object publicityManagementEffectiveness;

    /**
     *
     */
    private Object hostedSchoolLevelActivities;

    /**
     *
     */
    private String clubWorkIntroduction;

    /**
     *
     */
    private String imageUrl;

    /**
     *
     */
    private String clubEducationCaseAttachment;

    /**
     *
     */
    private Integer communistRelatedBackBoneNumber;

    /**
     *
     */
    private Integer backboneNumber;

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
        Studentclubevaluation other = (Studentclubevaluation) that;
        return (this.getRecordId() == null ? other.getRecordId() == null : this.getRecordId().equals(other.getRecordId()))
                && (this.getDeclarationYear() == null ? other.getDeclarationYear() == null : this.getDeclarationYear().equals(other.getDeclarationYear()))
                && (this.getClubId() == null ? other.getClubId() == null : this.getClubId().equals(other.getClubId()))
                && (this.getHandoverMethod() == null ? other.getHandoverMethod() == null : this.getHandoverMethod().equals(other.getHandoverMethod()))
                && (this.getHandoverParticipantsCount() == null ? other.getHandoverParticipantsCount() == null : this.getHandoverParticipantsCount().equals(other.getHandoverParticipantsCount()))
                && (this.getAdvisorParticipation() == null ? other.getAdvisorParticipation() == null : this.getAdvisorParticipation().equals(other.getAdvisorParticipation()))
                && (this.getMeetings() == null ? other.getMeetings() == null : this.getMeetings().equals(other.getMeetings()))
                && (this.getAssociationAwards() == null ? other.getAssociationAwards() == null : this.getAssociationAwards().equals(other.getAssociationAwards()))
                && (this.getPublicityManagementEffectiveness() == null ? other.getPublicityManagementEffectiveness() == null : this.getPublicityManagementEffectiveness().equals(other.getPublicityManagementEffectiveness()))
                && (this.getHostedSchoolLevelActivities() == null ? other.getHostedSchoolLevelActivities() == null : this.getHostedSchoolLevelActivities().equals(other.getHostedSchoolLevelActivities()))
                && (this.getClubWorkIntroduction() == null ? other.getClubWorkIntroduction() == null : this.getClubWorkIntroduction().equals(other.getClubWorkIntroduction()))
                && (this.getImageUrl() == null ? other.getImageUrl() == null : this.getImageUrl().equals(other.getImageUrl()))
                && (this.getClubEducationCaseAttachment() == null ? other.getClubEducationCaseAttachment() == null : this.getClubEducationCaseAttachment().equals(other.getClubEducationCaseAttachment()))
                && (this.getCommunistRelatedBackBoneNumber() == null ? other.getCommunistRelatedBackBoneNumber() == null : this.getCommunistRelatedBackBoneNumber().equals(other.getCommunistRelatedBackBoneNumber()))
                && (this.getBackboneNumber() == null ? other.getBackboneNumber() == null : this.getBackboneNumber().equals(other.getBackboneNumber()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getReviewOpinion() == null ? other.getReviewOpinion() == null : this.getReviewOpinion().equals(other.getReviewOpinion()));
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
        result = prime * result + ((getMeetings() == null) ? 0 : getMeetings().hashCode());
        result = prime * result + ((getAssociationAwards() == null) ? 0 : getAssociationAwards().hashCode());
        result = prime * result + ((getPublicityManagementEffectiveness() == null) ? 0 : getPublicityManagementEffectiveness().hashCode());
        result = prime * result + ((getHostedSchoolLevelActivities() == null) ? 0 : getHostedSchoolLevelActivities().hashCode());
        result = prime * result + ((getClubWorkIntroduction() == null) ? 0 : getClubWorkIntroduction().hashCode());
        result = prime * result + ((getImageUrl() == null) ? 0 : getImageUrl().hashCode());
        result = prime * result + ((getClubEducationCaseAttachment() == null) ? 0 : getClubEducationCaseAttachment().hashCode());
        result = prime * result + ((getCommunistRelatedBackBoneNumber() == null) ? 0 : getCommunistRelatedBackBoneNumber().hashCode());
        result = prime * result + ((getBackboneNumber() == null) ? 0 : getBackboneNumber().hashCode());
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
        sb.append(", clubId=").append(clubId);
        sb.append(", handoverMethod=").append(handoverMethod);
        sb.append(", handoverParticipantsCount=").append(handoverParticipantsCount);
        sb.append(", advisorParticipation=").append(advisorParticipation);
        sb.append(", meetings=").append(meetings);
        sb.append(", associationAwards=").append(associationAwards);
        sb.append(", publicityManagementEffectiveness=").append(publicityManagementEffectiveness);
        sb.append(", hostedSchoolLevelActivities=").append(hostedSchoolLevelActivities);
        sb.append(", clubWorkIntroduction=").append(clubWorkIntroduction);
        sb.append(", imageUrl=").append(imageUrl);
        sb.append(", clubEducationCaseAttachment=").append(clubEducationCaseAttachment);
        sb.append(", communistRelatedBackBoneNumber=").append(communistRelatedBackBoneNumber);
        sb.append(", backboneNumber=").append(backboneNumber);
        sb.append(", status=").append(status);
        sb.append(", reviewOpinion=").append(reviewOpinion);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}