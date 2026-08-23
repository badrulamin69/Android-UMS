package com.brilliantsoft.sms.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResultSummary {
    @SerializedName("studentDbId")
    private Long studentDbId;
    
    @SerializedName("studentId")
    private String studentId;
    
    @SerializedName("fullName")
    private String fullName;
    
    @SerializedName("programName")
    private String programName;
    
    @SerializedName("departmentName")
    private String departmentName;
    
    @SerializedName("facultyName")
    private String facultyName;
    
    @SerializedName("academicSessionName")
    private String academicSessionName;
    
    @SerializedName("currentSemester")
    private String currentSemester;
    
    @SerializedName("semesterGpa")
    private Double semesterGpa;
    
    @SerializedName("cgpa")
    private Double cgpa;
    
    @SerializedName("status")
    private String status;
}
