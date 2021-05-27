package com.developer.myapplication.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeedModel {

    @SerializedName("data")
    List<FeedDataModel> getFeedDataList;

    public List<FeedDataModel> getGetFeedDataList() {
        return getFeedDataList;
    }
}
