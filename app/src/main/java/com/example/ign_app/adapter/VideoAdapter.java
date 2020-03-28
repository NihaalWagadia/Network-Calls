package com.example.ign_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ign_app.networkcalls.CommentApi;
import com.example.ign_app.commentpackage.FeedComment;
import com.example.ign_app.commentpackage.content.ContentComment;
import com.example.ign_app.R;
import com.example.ign_app.helper.VideoConst;
import com.example.ign_app.activities.WebViewActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private  Context mContext;
    private  List<VideoConst> videoConstList;
    private static final String BASE_URL = "https://ign-apis.herokuapp.com/";
    ArrayList<ContentComment> contentCommentArrayList;


    public VideoAdapter(Context context, List<VideoConst> videoConstsList){
        this.mContext = context;
        this.videoConstList = videoConstsList;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.videocard, null);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VideoViewHolder holder, int position) {

        final  VideoConst videoConst = videoConstList.get(position);
        holder.title.setText(videoConst.getTitle());

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CommentApi CApi = retrofit.create(CommentApi.class);
        Call<FeedComment> call2 = CApi.getCommentCount(videoConst.getContentId());
        call2.enqueue(new Callback<FeedComment>() {
            @Override
            public void onResponse(Call<FeedComment> call, Response<FeedComment> response) {
                contentCommentArrayList= response.body().getContent();
                holder.commentValue.setText(contentCommentArrayList.get(0).getCount());

            }
            @Override
            public void onFailure(Call<FeedComment> call, Throwable t) {

            }
        });

        Picasso.with(mContext).load(videoConst.getImgUrl()).into(holder.imageView);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, WebViewActivity.class);
                i.putExtra("URL", videoConst.getVideoUrl());
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return videoConstList.size();
    }

    public void addItems(List<VideoConst> videoConsts) {
        videoConstList.addAll(videoConsts);
        notifyDataSetChanged();
    }

    class VideoViewHolder extends RecyclerView.ViewHolder{

        TextView title, commentValue;
        ImageView imageView;
        RelativeLayout relativeLayout;
        public VideoViewHolder(@NonNull View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.video_title);
            imageView = itemView.findViewById(R.id.video_image);
            relativeLayout = itemView.findViewById(R.id.relative_layout);
            commentValue = itemView.findViewById(R.id.Comments_count);

        }

    }

}
