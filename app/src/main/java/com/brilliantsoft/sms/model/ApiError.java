package com.brilliantsoft.sms.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ApiError {
    @SerializedName("message")
    private String message;
    @SerializedName("error")
    private String error;
    @SerializedName("status")
    private int status;
    @SerializedName("timestamp")
    private String timestamp;
}
