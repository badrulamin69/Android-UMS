package com.brilliantsoft.sms.model;

public class ExaminationResultRequest {
    private Double marks;
    private Double gradePoint;
    private String grade;
    private Double credit;
    private Long studentId;
    private Long examinationId;

    public ExaminationResultRequest() {}

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
    public Long getExaminationId() { return examinationId; }
    public void setExaminationId(Long examinationId) { this.examinationId = examinationId; }
}
