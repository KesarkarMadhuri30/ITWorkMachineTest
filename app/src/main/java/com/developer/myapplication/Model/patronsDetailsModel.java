package com.developer.myapplication.Model;

import com.google.gson.annotations.SerializedName;

public class patronsDetailsModel {
    @SerializedName("user_id")
    private String user_id;

    @SerializedName("user_first_name")
    private String user_first_name;

    @SerializedName("user_last_name")
    private String user_last_name;

    @SerializedName("user_profile_img")
    private String user_profile_img;

    public String getUser_id() {
        return user_id;
    }

    public String getUser_first_name() {
        return user_first_name;
    }

    public String getUser_last_name() {
        return user_last_name;
    }

    public String getUser_profile_img() {
        return user_profile_img;
    }
}
