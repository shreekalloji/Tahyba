package com.iprismtech.tahyba.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ShopsReviewsReq {
    @SerializedName("shopID")
    @Expose
    public String shopID;
    @SerializedName("start_from")
    @Expose
    public Integer startFrom;
    @SerializedName("userID")
    @Expose
    public String userID;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("shopID", shopID).append("startFrom", startFrom).append("userID", userID).toString();
    }

}
