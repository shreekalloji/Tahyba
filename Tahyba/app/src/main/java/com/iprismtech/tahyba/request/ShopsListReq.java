package com.iprismtech.tahyba.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ShopsListReq {
    @SerializedName("categoryID")
    @Expose
    public String categoryID;
    @SerializedName("user_lat")
    @Expose
    public String userLat;
    @SerializedName("user_long")
    @Expose
    public String userLong;
    @SerializedName("userID")
    @Expose
    public String userID;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("categoryID", categoryID).append("userLat", userLat).append("userLong", userLong).append("userID", userID).toString();
    }
}
