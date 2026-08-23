package com.brilliantsoft.sms.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultSheetResponse {
    @SerializedName("studentId")
    private Long studentId;
    @SerializedName("studentName")
    private String studentName;
    @SerializedName("semester")
    private String semester;
    @SerializedName("cgpa")
    private Double cgpa;
    @SerializedName("totalCredits")
    private Integer totalCredits;
    @SerializedName("rows")
    private List<ResultSheetCourseRow> rows;
}
