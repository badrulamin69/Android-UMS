package com.brilliantsoft.sms.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultSheetCourseRow {
    @SerializedName("courseCode")
    private String courseCode;
    @SerializedName("courseName")
    private String courseName;
    @SerializedName("credit")
    private Integer credit;
    @SerializedName("obtainedMarks")
    private Double obtainedMarks;
    @SerializedName("grade")
    private String grade;
    @SerializedName("gradePoint")
    private Double gradePoint;
}
