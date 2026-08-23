package com.brilliantsoft.sms.model;

import java.time.LocalDate;

public class EnrollmentResponse {
    private Long id;
    private LocalDate enrollmentDate;
    private String semester;
    private EnrollmentStatus status;
    private Long studentId;
    private String studentName;
    private Long courseId;
    private String courseName;

    public EnrollmentResponse() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(LocalDate enrollmentDate) { this.enrollmentDate = enrollmentDate; }

    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }

    public EnrollmentStatus getStatus() { return status; }
    public void setStatus(EnrollmentStatus status) { this.status = status; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
}
