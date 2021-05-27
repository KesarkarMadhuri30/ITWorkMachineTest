package com.developer.myapplication.Adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.developer.myapplication.Model.FeedDataModel;
import com.developer.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterFeedData extends RecyclerView.Adapter {
    Activity context;
    List<FeedDataModel> mFeedList = new ArrayList<>();
    int viewType;

    private static final int Organization = 1;
    private static final int Campaign = 2;


    public AdapterFeedData(Activity context, List<FeedDataModel> mFeedList) {
        this.context = context;
        this.mFeedList = mFeedList;
    }

    public int getItemViewType(int position) {
        if (mFeedList.get(position).getCategory().equals("organization")) {
            return Organization;
        } else if (mFeedList.get(position).getCategory().equals("campaign")) {
            return Campaign;
        }
        throw new RuntimeException("error");
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(context);
        switch (viewType) {
            case Organization:
                Log.d("$$$","Organization");
                View rootview = inflater.inflate(R.layout.organization_listitem, parent, false);
                viewHolder = new OrganizationViewHolder(rootview);
                break;
            case Campaign:
                Log.d("$$$","Campaign");
                View tworootview = inflater.inflate(R.layout.campgain_listitem, parent, false);
                viewHolder = new TwoViewHolder(tworootview);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case Organization:
                ((OrganizationViewHolder) holder).name.setText("" + mFeedList.get(position).getName());
                ((OrganizationViewHolder) holder).txt_description.setText("" + mFeedList.get(position).getDescription());
                ((OrganizationViewHolder) holder).txt_likes.setText("" + mFeedList.get(position).getLikes_count() + " Likes");

                Glide.with(context)
                        .load(mFeedList.get(position).getProfile_icon())
                        .placeholder(R.mipmap.ic_launcher)
                        .into(((OrganizationViewHolder) holder).profile_img);
                break;

            case Campaign:
                ((TwoViewHolder) holder).txt_name.setText("" + mFeedList.get(position).getName());
                ((TwoViewHolder) holder).txt_description.setText("" + mFeedList.get(position).getDescription());
                ((TwoViewHolder) holder).txt_likes.setText("" + mFeedList.get(position).getLikes_count() + " Likes");
                Glide.with(context)
                        .load(mFeedList.get(position).getProfile_icon())
                        .placeholder(R.mipmap.ic_launcher)
                        .into(((TwoViewHolder) holder).profile_img);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mFeedList.size();
    }

    public class OrganizationViewHolder extends RecyclerView.ViewHolder {
        TextView name, txt_description, txt_likes;
        ImageView profile_img;

        public OrganizationViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            txt_description = itemView.findViewById(R.id.txt_description);
            txt_likes = itemView.findViewById(R.id.txt_likes);

            profile_img = itemView.findViewById(R.id.profile_img);
        }
    }

    public class TwoViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name, txt_description, txt_likes;
        ImageView profile_img;
        RecyclerView user_recy;

        public TwoViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_description = itemView.findViewById(R.id.txt_description);
            txt_likes = itemView.findViewById(R.id.txt_likes);

            profile_img = itemView.findViewById(R.id.profile_img);
        }
    }
}
