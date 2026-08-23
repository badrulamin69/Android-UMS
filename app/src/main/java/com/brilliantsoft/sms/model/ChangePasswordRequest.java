package com.brilliantsoft.sms.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest {
    @SerializedName("currentPassword")
    private String currentPassword;
    @SerializedName("newPassword")
    private String newPassword;
    @SerializedName("confirmPassword")
    private String confirmPassword;
}
