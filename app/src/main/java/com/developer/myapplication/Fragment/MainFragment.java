package com.developer.myapplication.Fragment;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.developer.myapplication.API.RetrofitApiClient;
import com.developer.myapplication.API.RetrofitClientInstance;
import com.developer.myapplication.Adapter.AdapterFeedData;
import com.developer.myapplication.Model.FeedDataModel;
import com.developer.myapplication.Model.FeedModel;
import com.developer.myapplication.R;
import com.developer.myapplication.Util.Utility;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    RecyclerView feed_recy;
    AdapterFeedData adapterFeedData;
    List<FeedDataModel> feedList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment_main, container, false);

        feed_recy = rootview.findViewById(R.id.feed_recy);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        feed_recy.setLayoutManager(layoutManager);
        feed_recy.hasFixedSize();
        if (Utility.isNetworkAvailable(getContext())) {
            getFeedDataUrl();
        }
     else {
        Toast.makeText(getContext(), "No internet. Check Your Internet is connection", Toast.LENGTH_LONG).show();
    }
        return rootview;
    }

    private void getFeedDataUrl() {
        feedList.clear();
        ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
        //here is the trick:
        String progress = (getResources().getString(R.string.progress));
//
        progressDialog.setMessage(progress);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            progressDialog.setIndeterminateDrawable(getResources().getDrawable(R.drawable.progress_img, null));
        }
        progressDialog.show();



        RetrofitApiClient service = RetrofitClientInstance.getInstance().getApi();
        Call<FeedModel> feed_call = service.get_feed_list();

        feed_call.enqueue(new Callback<FeedModel>() {
            @Override
            public void onResponse(Call<FeedModel> call, Response<FeedModel> response) {
                if (response.body() != null) {
                        progressDialog.dismiss();
                        Log.e("###", "success response " + response.body());

                        if (response.body().getGetFeedDataList() != null) {
                            feedList = response.body().getGetFeedDataList();
                            Setfeed_Data(feedList);
                        }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "Some problems occurred, please try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FeedModel> call, Throwable t) {
                Toast.makeText(getContext(), "" + t, Toast.LENGTH_SHORT).show();
                 progressDialog.dismiss();
            }
        });
    }

    private void Setfeed_Data(List<FeedDataModel> feedList) {
        adapterFeedData = new AdapterFeedData(getActivity(),feedList);
        feed_recy.setAdapter(adapterFeedData);
    }
}