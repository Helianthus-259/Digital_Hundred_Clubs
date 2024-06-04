package org.example.dto;

import lombok.Data;
import org.example.entity.Backboneevaluation;
import org.example.entity.Student;

@Data
public class ExamDataDTO {
    String examName;
    Integer status;
    Integer examId;
    String examdetail;

    public ExamDataDTO(Backboneevaluation backboneevaluation, Student student)
    {
        this.examName = student.getStName();
    }
}
