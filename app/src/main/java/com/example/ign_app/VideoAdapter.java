package com.example.ign_app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private  Context mContext;
    private  List<VideoConst> videoConstList;

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
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {

        final  VideoConst videoConst = videoConstList.get(position);
        holder.title.setText(videoConst.getTitle());
        Picasso.with(mContext).load(videoConst.getImgUrl()).into(holder.imageView);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(videoConst.getVideoUrl()));
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return videoConstList.size();
    }

    class VideoViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView imageView;
        RelativeLayout relativeLayout;
        public VideoViewHolder(@NonNull View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.video_title);
            imageView = itemView.findViewById(R.id.video_image);
            relativeLayout = itemView.findViewById(R.id.relative_layout);

        }

    }

}
