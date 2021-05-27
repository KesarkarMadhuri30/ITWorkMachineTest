package com.developer.myapplication.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeedDataModel {
    @SerializedName("name")
    private String name;

    @SerializedName("category")
    private String category;

    @SerializedName("description")
    private String description;

    @SerializedName("image")
    private String image;

    @SerializedName("profile_icon")
    private String profile_icon;

    @SerializedName("likes_count")
    private int likes_count;

    @SerializedName("comments_count")
    private int comments_count;

    @SerializedName("campaign_name")
    private String campaign_name;

    @SerializedName("campaign_collection")
    private int campaign_collection;

    @SerializedName("campaign_goal")
    private int campaign_goal;

    @SerializedName("campaign_end_date")
    private int campaign_end_date;


    @SerializedName("patrons")
    PatronsModel getpatronsData;

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getProfile_icon() {
        return profile_icon;
    }

    public int getLikes_count() {
        return likes_count;
    }

    public int getComments_count() {
        return comments_count;
    }

    public String getCampaign_name() {
        return campaign_name;
    }

    public int getCampaign_collection() {
        return campaign_collection;
    }

    public int getCampaign_goal() {
        return campaign_goal;
    }

    public int getCampaign_end_date() {
        return campaign_end_date;
    }

    public PatronsModel getGetpatronsData() {
        return getpatronsData;
    }
}
