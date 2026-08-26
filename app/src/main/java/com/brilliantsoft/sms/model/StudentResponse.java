package com.brilliantsoft.sms.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {
    @SerializedName("id")
    private Long id;
    
    @SerializedName("studentId")
    private String studentId;
    
    @SerializedName(value = "fullName", alternate = {"name", "studentName", "displayName"})
    private String fullName;
    
    @SerializedName(value = "email", alternate = {"userEmail", "mail"})
    private String email;
    
    @SerializedName(value = "phone", alternate = {"mobile", "phoneNumber"})
    private String phone;
    
    @SerializedName("admissionDate")
    private String admissionDate;
    
    @SerializedName("userId")
    private Long userId;
    
    @SerializedName("applicantId")
    private Long applicantId;
    
    @SerializedName("programId")
    private Long programId;
    
    @SerializedName("programName")
    private String programName;
    
    @SerializedName("departmentName")
    private String departmentName;
    
    @SerializedName("academicSessionId")
    private Long academicSessionId;
    
    @SerializedName("sessionName")
    private String sessionName;

    // Compatibility aliases
    @SerializedName("uniqueCode")
    private String uniqueCode;
    
    @SerializedName("firstName")
    private String firstName;
    
    @SerializedName("lastName")
    private String lastName;
    
    @SerializedName("studentCode")
    private String studentCode;
    
    @SerializedName("status")
    private String status;
}
