package com.iprismtech.tahyba.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserSignUpReq {
    @SerializedName("full_name")
    @Expose
    public String fullName;
    @SerializedName("email_ID")
    @Expose
    public String emailID;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("Cpassword")
    @Expose
    public String cpassword;
    @SerializedName("Profile_pic")
    @Expose
    public String profilePic;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("fullName", fullName).append("emailID", emailID).append("mobile", mobile).append("password", password).append("cpassword", cpassword).append("profilePic", profilePic).toString();
    }
}
