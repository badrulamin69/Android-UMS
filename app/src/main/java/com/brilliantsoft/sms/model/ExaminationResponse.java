package com.brilliantsoft.sms.model;

import java.time.LocalDate;

public class ExaminationResponse {
    private Long id;
    private String examinationName;
    private String semester;
    private Double totalMarks;
    private Double passMarks;
    private LocalDate examinationDate;
    private Long courseId;
    private String courseName;

    public ExaminationResponse() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
}
