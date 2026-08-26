package com.brilliantsoft.sms.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentProfileResponse {
    @SerializedName("id")
    private Long id;
    
    @SerializedName("uniqueCode")
    private String uniqueCode;
    
    @SerializedName("studentId")
    private Long studentId;
    
    @SerializedName("address")
    private String address;
    
    @SerializedName("city")
    private String city;
    
    @SerializedName("state")
    private String state;
    
    @SerializedName("zipCode")
    private String zipCode;
    
    @SerializedName("nationality")
    private String nationality;
    
    @SerializedName("bloodGroup")
    private String bloodGroup;
    
    @SerializedName("emergencyContact")
    private String emergencyContact;
    
    @SerializedName("emergencyContactName")
    private String emergencyContactName;
    
    @SerializedName("medicalInfo")
    private String medicalInfo;
    
    @SerializedName("createdAt")
    private String createdAt;
    
    @SerializedName("updatedAt")
    private String updatedAt;

    // Enriched student details
    @SerializedName(value = "studentName", alternate = {"fullName", "name", "displayName"})
    private String studentName;
    
    @SerializedName(value = "studentCode", alternate = {"studentId", "uniqueCode", "code"})
    private String studentCode;
    
    @SerializedName(value = "email", alternate = {"userEmail", "contactEmail", "mail"})
    private String email;
    
    @SerializedName(value = "phone", alternate = {"mobile", "phoneNumber", "contactNumber", "cell"})
    private String phone;
    
    @SerializedName(value = "programName", alternate = {"program", "courseOfStudy"})
    private String programName;
    
    @SerializedName(value = "departmentName", alternate = {"department", "dept"})
    private String departmentName;
}
