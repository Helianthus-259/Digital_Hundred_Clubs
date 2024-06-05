package org.example.dto;

import lombok.Data;
import org.example.entity.Annualaudit;
import org.example.entity.Backboneevaluation;
import org.example.entity.Club;
import org.example.entity.Student;

@Data
public class ExamDataDTO {
    private String examName;
    private Integer status;
    private Integer examId;
    private String examdetail;

    public ExamDataDTO(Backboneevaluation backboneevaluation, Student student)
    {
        this.examName = student.getStName();
        this.status = backboneevaluation.getStatus();
        this.examId = backboneevaluation.getRecordId();
        this.examdetail = backboneevaluation.getReviewOpinion();
    }

    public ExamDataDTO(Annualaudit annualaudit, Club club)
    {
        this.examName = club.getClubName();
        this.status = annualaudit.getStatus();
        this.examId = annualaudit.getDeclarationId();
        this.examdetail = annualaudit.getReviewOpinion();
    }
}
