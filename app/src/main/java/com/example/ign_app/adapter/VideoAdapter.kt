package com.example.ign_app.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ign_app.R
import com.example.ign_app.activities.WebViewActivity
import com.example.ign_app.adapter.VideoAdapter.VideoViewHolder
import com.example.ign_app.commentpackage.CommentApiResponse
import com.example.ign_app.commentpackage.content.ContentComment
import com.example.ign_app.helper.VideoData
import com.example.ign_app.networkcalls.CommentApi
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.videocard.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class VideoAdapter(private val mContext: Context, private val videoDataList: MutableList<VideoData>) :
        RecyclerView.Adapter<VideoViewHolder>() {
    var contentCommentArrayList: ArrayList<ContentComment>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val view = inflater.inflate(R.layout.videocard, null)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        if(holder!=null){
            holder.card.video_title.text = videoDataList[position].title
            Picasso.with(mContext).load(videoDataList[position].imgUrl).into(holder.card.video_image)

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val commentApi = retrofit.create(CommentApi::class.java)
        val callComment = commentApi.getCommentCount(videoDataList[position].contentId)
            callComment.enqueue(object : Callback<CommentApiResponse> {
            override fun onResponse(call: Call<CommentApiResponse>, response: Response<CommentApiResponse>) {
                contentCommentArrayList = response.body()!!.content
                holder.card.Comments_count.text = contentCommentArrayList!![0].count
            }

            override fun onFailure(call: Call<CommentApiResponse>, t: Throwable) {}
        })
        holder.card.relative_layout.setOnClickListener {
            val i = Intent(mContext, WebViewActivity::class.java)
            i.putExtra("URL", videoDataList[position].videoUrl)
            mContext.startActivity(i)
        }
        }
    }

    override fun getItemCount(): Int {
        return videoDataList!!.size
    }

    fun addItems(videoData: Collection<VideoData>) {
        videoDataList?.addAll(videoData)
        notifyDataSetChanged()
    }

    class VideoViewHolder(val card: View) : RecyclerView.ViewHolder(card)


    companion object {
        private const val BASE_URL = "https://ign-apis.herokuapp.com/"
    }

}