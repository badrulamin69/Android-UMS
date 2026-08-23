package com.brilliantsoft.sms.model;

import java.time.LocalDate;

public class ExaminationRequest {
    private String examinationName;
    private String semester;
    private Double totalMarks;
    private Double passMarks;
    private LocalDate examinationDate;
    private Long courseId;

    public ExaminationRequest() {}

    public String getExaminationName() { return examinationName; }
    public void setExaminationName(String examinationName) { this.examinationName = examinationName; }
    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }
    public Double getTotalMarks() { return totalMarks; }
    public void setTotalMarks(Double totalMarks) { this.totalMarks = totalMarks; }
    public Double getPassMarks() { return passMarks; }
    public void setPassMarks(Double passMarks) { this.passMarks = passMarks; }
    public LocalDate getExaminationDate() { return examinationDate; }
    public void setExaminationDate(LocalDate examinationDate) { this.examinationDate = examinationDate; }
    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
}
