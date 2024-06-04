package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName annualaudit
 */
@TableName(value ="annualaudit")
@Data
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

    /**
     *
     */
    private Object publicityManagementInfo;

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
        Annualaudit other = (Annualaudit) that;
        return (this.getDeclarationId() == null ? other.getDeclarationId() == null : this.getDeclarationId().equals(other.getDeclarationId()))
                && (this.getDeclarationYear() == null ? other.getDeclarationYear() == null : this.getDeclarationYear().equals(other.getDeclarationYear()))
                && (this.getClubId() == null ? other.getClubId() == null : this.getClubId().equals(other.getClubId()))
                && (this.getReviewResult() == null ? other.getReviewResult() == null : this.getReviewResult().equals(other.getReviewResult()))
                && (this.getMeetingActivityListAttachment() == null ? other.getMeetingActivityListAttachment() == null : this.getMeetingActivityListAttachment().equals(other.getMeetingActivityListAttachment()))
                && (this.getExternalSponsorshipAttachment() == null ? other.getExternalSponsorshipAttachment() == null : this.getExternalSponsorshipAttachment().equals(other.getExternalSponsorshipAttachment()))
                && (this.getIsFinancialInformationPublic() == null ? other.getIsFinancialInformationPublic() == null : this.getIsFinancialInformationPublic().equals(other.getIsFinancialInformationPublic()))
                && (this.getClubConstitutionAttachment() == null ? other.getClubConstitutionAttachment() == null : this.getClubConstitutionAttachment().equals(other.getClubConstitutionAttachment()))
                && (this.getDepartmentOpinion() == null ? other.getDepartmentOpinion() == null : this.getDepartmentOpinion().equals(other.getDepartmentOpinion()))
                && (this.getPublicityManagementInfo() == null ? other.getPublicityManagementInfo() == null : this.getPublicityManagementInfo().equals(other.getPublicityManagementInfo()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getReviewOpinion() == null ? other.getReviewOpinion() == null : this.getReviewOpinion().equals(other.getReviewOpinion()));
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
        result = prime * result + ((getPublicityManagementInfo() == null) ? 0 : getPublicityManagementInfo().hashCode());
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
        sb.append(", declarationId=").append(declarationId);
        sb.append(", declarationYear=").append(declarationYear);
        sb.append(", clubId=").append(clubId);
        sb.append(", reviewResult=").append(reviewResult);
        sb.append(", meetingActivityListAttachment=").append(meetingActivityListAttachment);
        sb.append(", externalSponsorshipAttachment=").append(externalSponsorshipAttachment);
        sb.append(", isFinancialInformationPublic=").append(isFinancialInformationPublic);
        sb.append(", clubConstitutionAttachment=").append(clubConstitutionAttachment);
        sb.append(", departmentOpinion=").append(departmentOpinion);
        sb.append(", publicityManagementInfo=").append(publicityManagementInfo);
        sb.append(", status=").append(status);
        sb.append(", reviewOpinion=").append(reviewOpinion);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}