package com.brilliantsoft.sms.model;

import java.time.LocalDate;

public class EnrollmentRequest {
    private LocalDate enrollmentDate;
    private String semester;
    private EnrollmentStatus status;
    private Long studentId;
    private Long courseId;

    public EnrollmentRequest() {}

    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(LocalDate enrollmentDate) { this.enrollmentDate = enrollmentDate; }

    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }

    public EnrollmentStatus getStatus() { return status; }
    public void setStatus(EnrollmentStatus status) { this.status = status; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
}
