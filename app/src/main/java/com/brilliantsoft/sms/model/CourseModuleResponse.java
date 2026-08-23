package com.brilliantsoft.sms.model;

public class CourseModuleResponse {
    private Long id;
    private String moduleTitle;
    private Integer moduleOrder;
    private String description;
    private Long courseId;
    private String courseName;

    public CourseModuleResponse() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getModuleTitle() { return moduleTitle; }
    public void setModuleTitle(String moduleTitle) { this.moduleTitle = moduleTitle; }
    public Integer getModuleOrder() { return moduleOrder; }
    public void setModuleOrder(Integer moduleOrder) { this.moduleOrder = moduleOrder; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
}
