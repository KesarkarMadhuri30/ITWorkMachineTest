package com.developer.myapplication.API;

import com.developer.myapplication.Model.FeedModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RetrofitApiClient {

    @GET("feed_data.json")
    Call<FeedModel> get_feed_list();
}
