package com.developer.myapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.developer.myapplication.Model.FeedDataModel;
import com.developer.myapplication.Model.PatronsModel;
import com.developer.myapplication.Model.patronsDetailsModel;
import com.developer.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterFeedData extends RecyclerView.Adapter {
    Activity context;
    List<FeedDataModel> mFeedList = new ArrayList<>();
    List<patronsDetailsModel> mpatronList = new ArrayList<>();
    int viewType;

    private static final int Organization = 1;
    private static final int Campaign = 2;
    AdapterPatrons adapterPatrons;

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
                ((OrganizationViewHolder) holder).txt_likes.setText("" + mFeedList.get(position).getLikes_count() + " Likes");


                String str = mFeedList.get(position).getDescription();

                String[] str_array = str.split(" ");

                for (int i = 0; i < str_array.length; i++) {
                    if (str_array[i].startsWith(".com")) {
                        str=str.replaceAll(str_array[i], "<font color='#EE0000'>" + str_array[i] + "</font>");
                    } else if (str_array[i].startsWith("#")) {
                        str=str.replaceAll(str_array[i], "<font color='#EEE000'>" + str_array[i] + "</font>");
                    }
                }

                ((OrganizationViewHolder) holder).txt_description.setText(Html.fromHtml(str));

                Glide.with(context)
                        .load(mFeedList.get(position).getProfile_icon())
                        .placeholder(R.mipmap.ic_launcher)
                        .into(((OrganizationViewHolder) holder).profile_img);
                break;

            case Campaign:
                ((TwoViewHolder) holder).txt_name.setText("" + mFeedList.get(position).getName());
                ((TwoViewHolder) holder).txt_likes.setText("" + mFeedList.get(position).getLikes_count() + " Likes");
                Glide.with(context)
                        .load(mFeedList.get(position).getProfile_icon())
                        .placeholder(R.mipmap.ic_launcher)
                        .into(((TwoViewHolder) holder).profile_img);


                String camp_str = mFeedList.get(position).getDescription();

                String[] str_camparray = camp_str.split(" ");

                for (int i = 0; i < str_camparray.length; i++) {
                    if (str_camparray[i].startsWith(".com")) {
                        camp_str=camp_str.replaceAll(str_camparray[i], "<font color='#EE0000'>" + str_camparray[i] + "</font>");
                    } else if (str_camparray[i].startsWith("#")) {
                        camp_str=camp_str.replaceAll(str_camparray[i], "<font color='#EEE000'>" + str_camparray[i] + "</font>");
                    }
                }

                ((TwoViewHolder) holder).txt_description.setText(Html.fromHtml(camp_str));



                ((TwoViewHolder) holder).user_recy.hasFixedSize();
                LinearLayoutManager layoutManager = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
                ((TwoViewHolder) holder).user_recy.setLayoutManager(layoutManager);
                ((TwoViewHolder) holder).user_recy.setItemAnimator(new DefaultItemAnimator());
                PatronsModel patronsModel = mFeedList.get(position).getGetpatronsData();
                mpatronList = patronsModel.getGetpatronsDetails();
                if(mpatronList.size() !=0)
                {

                    ((TwoViewHolder) holder).user_recy.setVisibility(View.VISIBLE);
                    adapterPatrons = new AdapterPatrons(context,mpatronList);
                    ((TwoViewHolder) holder).user_recy.setAdapter(adapterPatrons);
                }
                else
                {
                    ((TwoViewHolder) holder).user_recy.setVisibility(View.GONE);
                }
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
            user_recy = itemView.findViewById(R.id.user_recy);
        }
    }

    private class AdapterPatrons extends RecyclerView.Adapter<AdapterPatrons.ViewHolder> {
        Context context;
        List<patronsDetailsModel> mpatronDataList;

        public AdapterPatrons(Context context, List<patronsDetailsModel> mpatronList) {
            this.context = context;
            this.mpatronDataList = mpatronList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View rootview = inflater.inflate(R.layout.patrons_listitem, parent, false);
            return new ViewHolder(rootview);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Glide.with(context)
                    .load(mpatronDataList.get(position).getUser_profile_img())
                    .placeholder(R.drawable.download)
                    .into(holder.userprofile);
        }

        @Override
        public int getItemCount() {
            return mpatronDataList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            CircleImageView userprofile;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                userprofile = itemView.findViewById(R.id.userprofile);
            }
        }
    }
}
