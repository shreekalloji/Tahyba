package com.iprismtech.tahyba.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ResetPasswordRequest {
    @SerializedName("userID")
    @Expose
    public String userID;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("Cpassword")
    @Expose
    public String cpassword;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("userID", userID).append("password", password).append("cpassword", cpassword).toString();
    }
}
