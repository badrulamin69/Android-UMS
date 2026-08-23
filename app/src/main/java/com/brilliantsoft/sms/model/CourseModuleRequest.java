package com.brilliantsoft.sms.model;

public class CourseModuleRequest {
    private String moduleTitle;
    private Integer moduleOrder;
    private String description;
    private Long courseId;

    public CourseModuleRequest() {}

    public String getModuleTitle() { return moduleTitle; }
    public void setModuleTitle(String moduleTitle) { this.moduleTitle = moduleTitle; }
    public Integer getModuleOrder() { return moduleOrder; }
    public void setModuleOrder(Integer moduleOrder) { this.moduleOrder = moduleOrder; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
}
