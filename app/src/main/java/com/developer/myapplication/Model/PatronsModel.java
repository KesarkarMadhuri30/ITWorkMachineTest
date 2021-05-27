package com.developer.myapplication.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PatronsModel {
    @SerializedName("details")
    List<patronsDetailsModel> getpatronsDetails;

    @SerializedName("count")
    private int count;

    public List<patronsDetailsModel> getGetpatronsDetails() {
        return getpatronsDetails;
    }

    public int getCount() {
        return count;
    }
}
