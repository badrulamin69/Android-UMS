package com.brilliantsoft.sms.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequest {
    @SerializedName(value = "courseName", alternate = {"name", "title"})
    private String courseName;

    @SerializedName(value = "courseCode", alternate = {"code", "course_code"})
    private String courseCode;

    @SerializedName(value = "credit", alternate = {"durationYears", "duration_years", "credits"})
    private Integer credit;

    private String description;
    private Long departmentId;
    private Long programId;
}
