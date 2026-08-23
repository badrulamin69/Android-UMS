package com.brilliantsoft.sms.model;

public class ExaminationResultResponse {
    private Long id;
    private Double marks;
    private Double gradePoint;
    private String grade;
    private Double credit;
    private Long studentId;
    private String studentName;
    private Long examinationId;
    private String examinationName;

    public ExaminationResultResponse() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Double getMarks() { return marks; }
    public void setMarks(Double marks) { this.marks = marks; }
    public Double getGradePoint() { return gradePoint; }
    public void setGradePoint(Double gradePoint) { this.gradePoint = gradePoint; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
    public Double getCredit() { return credit; }
    public void setCredit(Double credit) { this.credit = credit; }
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public Long getExaminationId() { return examinationId; }
    public void setExaminationId(Long examinationId) { this.examinationId = examinationId; }
    public String getExaminationName() { return examinationName; }
    public void setExaminationName(String examinationName) { this.examinationName = examinationName; }
}
