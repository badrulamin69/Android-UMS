package com.brilliantsoft.sms.model;

import com.google.gson.annotations.SerializedName;

public class CourseResponse {
    private Long id;
    private String courseName;
    private String courseCode;
    private Integer credit;
    private String description;
    private Long departmentId;
    private String departmentName;
    private Long programId;
    private String programName;

    public CourseResponse() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public Integer getCredit() { return credit; }
    public void setCredit(Integer credit) { this.credit = credit; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Long getDepartmentId() { return departmentId; }
    public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; }
    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }
    public Long getProgramId() { return programId; }
    public void setProgramId(Long programId) { this.programId = programId; }
    public String getProgramName() { return programName; }
    public void setProgramName(String programName) { this.programName = programName; }

    // Compatibility getters
    public String getName() { return courseName; }
    public String getCode() { return courseCode; }
    public Integer getDurationYears() { return credit; }
}
