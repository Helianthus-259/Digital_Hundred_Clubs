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
 * @TableName club
 */
@TableName(value ="club")
@Data
public class Club implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer clubId;

    /**
     * 
     */
    private String clubName;

    /**
     * 
     */
    private Date establishmentDate;

    /**
     * 
     */
    private Integer responsibleDepartmentId;

    /**
     * 
     */
    private String clubDescription;

    /**
     * 
     */
    private Integer clubCategory;

    /**
     * 
     */
    private String administrativeGuideTeacherName;

    /**
     * 
     */
    private String businessGuideTeacherName;

    /**
     * 
     */
    private Integer contactPersonId;

    /**
     * 
     */
    private Integer clubStatus;

    /**
     * 
     */
    private Integer mainCampus;

    /**
     * 
     */
    private Integer totalMembers;

    /**
     * 
     */
    private Integer isFinancialInformationPublic;

    /**
     * 
     */
    private Object publicityManagementInfo;

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
        Club other = (Club) that;
        return (this.getClubId() == null ? other.getClubId() == null : this.getClubId().equals(other.getClubId()))
            && (this.getClubName() == null ? other.getClubName() == null : this.getClubName().equals(other.getClubName()))
            && (this.getEstablishmentDate() == null ? other.getEstablishmentDate() == null : this.getEstablishmentDate().equals(other.getEstablishmentDate()))
            && (this.getResponsibleDepartmentId() == null ? other.getResponsibleDepartmentId() == null : this.getResponsibleDepartmentId().equals(other.getResponsibleDepartmentId()))
            && (this.getClubDescription() == null ? other.getClubDescription() == null : this.getClubDescription().equals(other.getClubDescription()))
            && (this.getClubCategory() == null ? other.getClubCategory() == null : this.getClubCategory().equals(other.getClubCategory()))
            && (this.getAdministrativeGuideTeacherName() == null ? other.getAdministrativeGuideTeacherName() == null : this.getAdministrativeGuideTeacherName().equals(other.getAdministrativeGuideTeacherName()))
            && (this.getBusinessGuideTeacherName() == null ? other.getBusinessGuideTeacherName() == null : this.getBusinessGuideTeacherName().equals(other.getBusinessGuideTeacherName()))
            && (this.getContactPersonId() == null ? other.getContactPersonId() == null : this.getContactPersonId().equals(other.getContactPersonId()))
            && (this.getClubStatus() == null ? other.getClubStatus() == null : this.getClubStatus().equals(other.getClubStatus()))
            && (this.getMainCampus() == null ? other.getMainCampus() == null : this.getMainCampus().equals(other.getMainCampus()))
            && (this.getTotalMembers() == null ? other.getTotalMembers() == null : this.getTotalMembers().equals(other.getTotalMembers()))
            && (this.getIsFinancialInformationPublic() == null ? other.getIsFinancialInformationPublic() == null : this.getIsFinancialInformationPublic().equals(other.getIsFinancialInformationPublic()))
            && (this.getPublicityManagementInfo() == null ? other.getPublicityManagementInfo() == null : this.getPublicityManagementInfo().equals(other.getPublicityManagementInfo()))
            && (this.getImageUrl() == null ? other.getImageUrl() == null : this.getImageUrl().equals(other.getImageUrl()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getClubId() == null) ? 0 : getClubId().hashCode());
        result = prime * result + ((getClubName() == null) ? 0 : getClubName().hashCode());
        result = prime * result + ((getEstablishmentDate() == null) ? 0 : getEstablishmentDate().hashCode());
        result = prime * result + ((getResponsibleDepartmentId() == null) ? 0 : getResponsibleDepartmentId().hashCode());
        result = prime * result + ((getClubDescription() == null) ? 0 : getClubDescription().hashCode());
        result = prime * result + ((getClubCategory() == null) ? 0 : getClubCategory().hashCode());
        result = prime * result + ((getAdministrativeGuideTeacherName() == null) ? 0 : getAdministrativeGuideTeacherName().hashCode());
        result = prime * result + ((getBusinessGuideTeacherName() == null) ? 0 : getBusinessGuideTeacherName().hashCode());
        result = prime * result + ((getContactPersonId() == null) ? 0 : getContactPersonId().hashCode());
        result = prime * result + ((getClubStatus() == null) ? 0 : getClubStatus().hashCode());
        result = prime * result + ((getMainCampus() == null) ? 0 : getMainCampus().hashCode());
        result = prime * result + ((getTotalMembers() == null) ? 0 : getTotalMembers().hashCode());
        result = prime * result + ((getIsFinancialInformationPublic() == null) ? 0 : getIsFinancialInformationPublic().hashCode());
        result = prime * result + ((getPublicityManagementInfo() == null) ? 0 : getPublicityManagementInfo().hashCode());
        result = prime * result + ((getImageUrl() == null) ? 0 : getImageUrl().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", clubId=").append(clubId);
        sb.append(", clubName=").append(clubName);
        sb.append(", establishmentDate=").append(establishmentDate);
        sb.append(", responsibleDepartmentId=").append(responsibleDepartmentId);
        sb.append(", clubDescription=").append(clubDescription);
        sb.append(", clubCategory=").append(clubCategory);
        sb.append(", administrativeGuideTeacherName=").append(administrativeGuideTeacherName);
        sb.append(", businessGuideTeacherName=").append(businessGuideTeacherName);
        sb.append(", contactPersonId=").append(contactPersonId);
        sb.append(", clubStatus=").append(clubStatus);
        sb.append(", mainCampus=").append(mainCampus);
        sb.append(", totalMembers=").append(totalMembers);
        sb.append(", isFinancialInformationPublic=").append(isFinancialInformationPublic);
        sb.append(", publicityManagementInfo=").append(publicityManagementInfo);
        sb.append(", imageUrl=").append(imageUrl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}