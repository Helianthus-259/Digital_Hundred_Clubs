package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 
 * @TableName annualaudit
 */
@TableName(value ="annualaudit")
public class Annualaudit implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer declarationId;

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
    private String reviewResult;

    /**
     * 
     */
    private String meetingActivityListAttachment;

    /**
     * 
     */
    private String externalSponsorshipAttachment;

    /**
     * 
     */
    private Integer isFinancialInformationPublic;

    /**
     * 
     */
    private String clubConstitutionAttachment;

    /**
     * 
     */
    private String departmentOpinion;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getDeclarationId() {
        return declarationId;
    }

    /**
     * 
     */
    public void setDeclarationId(Integer declarationId) {
        this.declarationId = declarationId;
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
    public String getReviewResult() {
        return reviewResult;
    }

    /**
     * 
     */
    public void setReviewResult(String reviewResult) {
        this.reviewResult = reviewResult;
    }

    /**
     * 
     */
    public String getMeetingActivityListAttachment() {
        return meetingActivityListAttachment;
    }

    /**
     * 
     */
    public void setMeetingActivityListAttachment(String meetingActivityListAttachment) {
        this.meetingActivityListAttachment = meetingActivityListAttachment;
    }

    /**
     * 
     */
    public String getExternalSponsorshipAttachment() {
        return externalSponsorshipAttachment;
    }

    /**
     * 
     */
    public void setExternalSponsorshipAttachment(String externalSponsorshipAttachment) {
        this.externalSponsorshipAttachment = externalSponsorshipAttachment;
    }

    /**
     * 
     */
    public Integer getIsFinancialInformationPublic() {
        return isFinancialInformationPublic;
    }

    /**
     * 
     */
    public void setIsFinancialInformationPublic(Integer isFinancialInformationPublic) {
        this.isFinancialInformationPublic = isFinancialInformationPublic;
    }

    /**
     * 
     */
    public String getClubConstitutionAttachment() {
        return clubConstitutionAttachment;
    }

    /**
     * 
     */
    public void setClubConstitutionAttachment(String clubConstitutionAttachment) {
        this.clubConstitutionAttachment = clubConstitutionAttachment;
    }

    /**
     * 
     */
    public String getDepartmentOpinion() {
        return departmentOpinion;
    }

    /**
     * 
     */
    public void setDepartmentOpinion(String departmentOpinion) {
        this.departmentOpinion = departmentOpinion;
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
        Annualaudit other = (Annualaudit) that;
        return (this.getDeclarationId() == null ? other.getDeclarationId() == null : this.getDeclarationId().equals(other.getDeclarationId()))
            && (this.getDeclarationYear() == null ? other.getDeclarationYear() == null : this.getDeclarationYear().equals(other.getDeclarationYear()))
            && (this.getClubId() == null ? other.getClubId() == null : this.getClubId().equals(other.getClubId()))
            && (this.getReviewResult() == null ? other.getReviewResult() == null : this.getReviewResult().equals(other.getReviewResult()))
            && (this.getMeetingActivityListAttachment() == null ? other.getMeetingActivityListAttachment() == null : this.getMeetingActivityListAttachment().equals(other.getMeetingActivityListAttachment()))
            && (this.getExternalSponsorshipAttachment() == null ? other.getExternalSponsorshipAttachment() == null : this.getExternalSponsorshipAttachment().equals(other.getExternalSponsorshipAttachment()))
            && (this.getIsFinancialInformationPublic() == null ? other.getIsFinancialInformationPublic() == null : this.getIsFinancialInformationPublic().equals(other.getIsFinancialInformationPublic()))
            && (this.getClubConstitutionAttachment() == null ? other.getClubConstitutionAttachment() == null : this.getClubConstitutionAttachment().equals(other.getClubConstitutionAttachment()))
            && (this.getDepartmentOpinion() == null ? other.getDepartmentOpinion() == null : this.getDepartmentOpinion().equals(other.getDepartmentOpinion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDeclarationId() == null) ? 0 : getDeclarationId().hashCode());
        result = prime * result + ((getDeclarationYear() == null) ? 0 : getDeclarationYear().hashCode());
        result = prime * result + ((getClubId() == null) ? 0 : getClubId().hashCode());
        result = prime * result + ((getReviewResult() == null) ? 0 : getReviewResult().hashCode());
        result = prime * result + ((getMeetingActivityListAttachment() == null) ? 0 : getMeetingActivityListAttachment().hashCode());
        result = prime * result + ((getExternalSponsorshipAttachment() == null) ? 0 : getExternalSponsorshipAttachment().hashCode());
        result = prime * result + ((getIsFinancialInformationPublic() == null) ? 0 : getIsFinancialInformationPublic().hashCode());
        result = prime * result + ((getClubConstitutionAttachment() == null) ? 0 : getClubConstitutionAttachment().hashCode());
        result = prime * result + ((getDepartmentOpinion() == null) ? 0 : getDepartmentOpinion().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", declarationId=").append(declarationId);
        sb.append(", declarationYear=").append(declarationYear);
        sb.append(", clubId=").append(clubId);
        sb.append(", reviewResult=").append(reviewResult);
        sb.append(", meetingActivityListAttachment=").append(meetingActivityListAttachment);
        sb.append(", externalSponsorshipAttachment=").append(externalSponsorshipAttachment);
        sb.append(", isFinancialInformationPublic=").append(isFinancialInformationPublic);
        sb.append(", clubConstitutionAttachment=").append(clubConstitutionAttachment);
        sb.append(", departmentOpinion=").append(departmentOpinion);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}