package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName clubapplicationrecord
 */
@TableName(value ="clubapplicationrecord")
@Data
public class Clubapplicationrecord implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer recordId;

    /**
     * 
     */
    private Integer clubId;

    /**
     * 
     */
    private Integer collegeReviewStatus;

    /**
     * 
     */
    private Integer universityStudentUnionReviewStatus;

    /**
     * 
     */
    private String collegeReviewOpinion;

    /**
     * 
     */
    private String universityStudentUnionReviewOpinion;

    /**
     * 
     */
    private String attachmentUrl;

    /**
     * 
     */
    private String advisorResumeAttachmentUrl;

    /**
     * 
     */
    private Integer studentId;

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
        Clubapplicationrecord other = (Clubapplicationrecord) that;
        return (this.getRecordId() == null ? other.getRecordId() == null : this.getRecordId().equals(other.getRecordId()))
            && (this.getClubId() == null ? other.getClubId() == null : this.getClubId().equals(other.getClubId()))
            && (this.getCollegeReviewStatus() == null ? other.getCollegeReviewStatus() == null : this.getCollegeReviewStatus().equals(other.getCollegeReviewStatus()))
            && (this.getUniversityStudentUnionReviewStatus() == null ? other.getUniversityStudentUnionReviewStatus() == null : this.getUniversityStudentUnionReviewStatus().equals(other.getUniversityStudentUnionReviewStatus()))
            && (this.getCollegeReviewOpinion() == null ? other.getCollegeReviewOpinion() == null : this.getCollegeReviewOpinion().equals(other.getCollegeReviewOpinion()))
            && (this.getUniversityStudentUnionReviewOpinion() == null ? other.getUniversityStudentUnionReviewOpinion() == null : this.getUniversityStudentUnionReviewOpinion().equals(other.getUniversityStudentUnionReviewOpinion()))
            && (this.getAttachmentUrl() == null ? other.getAttachmentUrl() == null : this.getAttachmentUrl().equals(other.getAttachmentUrl()))
            && (this.getAdvisorResumeAttachmentUrl() == null ? other.getAdvisorResumeAttachmentUrl() == null : this.getAdvisorResumeAttachmentUrl().equals(other.getAdvisorResumeAttachmentUrl()))
            && (this.getStudentId() == null ? other.getStudentId() == null : this.getStudentId().equals(other.getStudentId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRecordId() == null) ? 0 : getRecordId().hashCode());
        result = prime * result + ((getClubId() == null) ? 0 : getClubId().hashCode());
        result = prime * result + ((getCollegeReviewStatus() == null) ? 0 : getCollegeReviewStatus().hashCode());
        result = prime * result + ((getUniversityStudentUnionReviewStatus() == null) ? 0 : getUniversityStudentUnionReviewStatus().hashCode());
        result = prime * result + ((getCollegeReviewOpinion() == null) ? 0 : getCollegeReviewOpinion().hashCode());
        result = prime * result + ((getUniversityStudentUnionReviewOpinion() == null) ? 0 : getUniversityStudentUnionReviewOpinion().hashCode());
        result = prime * result + ((getAttachmentUrl() == null) ? 0 : getAttachmentUrl().hashCode());
        result = prime * result + ((getAdvisorResumeAttachmentUrl() == null) ? 0 : getAdvisorResumeAttachmentUrl().hashCode());
        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", recordId=").append(recordId);
        sb.append(", clubId=").append(clubId);
        sb.append(", collegeReviewStatus=").append(collegeReviewStatus);
        sb.append(", universityStudentUnionReviewStatus=").append(universityStudentUnionReviewStatus);
        sb.append(", collegeReviewOpinion=").append(collegeReviewOpinion);
        sb.append(", universityStudentUnionReviewOpinion=").append(universityStudentUnionReviewOpinion);
        sb.append(", attachmentUrl=").append(attachmentUrl);
        sb.append(", advisorResumeAttachmentUrl=").append(advisorResumeAttachmentUrl);
        sb.append(", studentId=").append(studentId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}