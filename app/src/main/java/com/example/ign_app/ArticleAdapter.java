package com.example.ign_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticleAdapter extends  RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {


    private Context mContext;
    private List<ArticleData> articleDataList;

    public ArticleAdapter(Context mContext, List<ArticleData> articleDataList) {
        this.mContext = mContext;
        this.articleDataList = articleDataList;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.articlecard, null);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {

        ArticleData articleData = articleDataList.get(position);
        holder.head_txt.setText(articleData.getHeadline());
        holder.des_txt.setText(articleData.getDescription());
        holder.authorName_txt.setText(articleData.getAuthorName());
        Picasso.with(mContext).load(articleData.getUrlImage()).into(holder.article_image);
        Picasso.with(mContext).load(articleData.getAuthorImage()).into(holder.author_image);



    }

    @Override
    public int getItemCount() {
        return articleDataList.size();
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder{

        TextView head_txt, des_txt, authorName_txt;
        ImageView article_image, author_image;
        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            head_txt = itemView.findViewById(R.id.headline_article);
            des_txt = itemView.findViewById(R.id.article_description);
            authorName_txt = itemView.findViewById(R.id.article_author_name);
            article_image = itemView.findViewById(R.id.article_image);
            author_image = itemView.findViewById(R.id.article_author);
        }
    }
}