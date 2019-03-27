package com.iprismtech.tahyba.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserAddratingReq {
    @SerializedName("shopID")
    @Expose
    public String shopID;
    @SerializedName("rating")
    @Expose
    public Integer rating;
    @SerializedName("userID")
    @Expose
    public String userID;
    @SerializedName("revMsg")
    @Expose
    public String revMsg;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("shopID", shopID).append("rating", rating).append("userID", userID).append("revMsg", revMsg).toString();
    }
}