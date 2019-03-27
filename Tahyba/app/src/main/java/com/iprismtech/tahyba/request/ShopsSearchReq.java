package com.iprismtech.tahyba.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ShopsSearchReq {
    @SerializedName("userID")
    @Expose
    public String userID;
    @SerializedName("key_words")
    @Expose
    public String keyWords;
    @SerializedName("user_lat")
    @Expose
    public String userLat;
    @SerializedName("user_long")
    @Expose
    public String userLong;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("userID", userID).append("keyWords", keyWords).append("userLat", userLat).append("userLong", userLong).toString();
    }

}
