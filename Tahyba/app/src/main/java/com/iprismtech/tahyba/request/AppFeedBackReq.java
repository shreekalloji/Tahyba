package com.iprismtech.tahyba.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AppFeedBackReq {
    @SerializedName("userID")
    @Expose
    public String userID;
    @SerializedName("feedMsg")
    @Expose
    public String feedMsg;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("userID", userID).append("feedMsg", feedMsg).toString();
    }
}
