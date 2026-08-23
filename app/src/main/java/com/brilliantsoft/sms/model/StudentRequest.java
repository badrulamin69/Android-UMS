package com.brilliantsoft.sms.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {
    @SerializedName("studentId")
    private String studentId;
    
    @SerializedName("fullName")
    private String fullName;
    
    @SerializedName("phone")
    private String phone;
    
    @SerializedName("admissionDate")
    private String admissionDate;
    
    @SerializedName("userId")
    private Long userId;
    
    @SerializedName("applicantId")
    private Long applicantId;
    
    @SerializedName("programId")
    private Long programId;
    
    @SerializedName("academicSessionId")
    private Long academicSessionId;
}
