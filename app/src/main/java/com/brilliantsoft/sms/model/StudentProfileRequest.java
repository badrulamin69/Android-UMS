package com.brilliantsoft.sms.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentProfileRequest {
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
}
