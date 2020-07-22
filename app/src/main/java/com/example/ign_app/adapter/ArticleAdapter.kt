package com.example.ign_app.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ign_app.R
import com.example.ign_app.activities.ArticleTab
import com.example.ign_app.activities.WebViewActivity
import com.example.ign_app.commentpackage.CommentApiResponse
import com.example.ign_app.commentpackage.content.ContentComment
import com.example.ign_app.helper.ArticleData
import com.example.ign_app.networkcalls.CommentApi
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.articlecard.view.*
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import com.example.ign_app.adapter.ArticleAdapter.ArticleViewHolder

import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

 class ArticleAdapter(val mContext: Context, private val articleDataList: MutableList<ArticleData>) :
        RecyclerView.Adapter<ArticleViewHolder>() {
    var contentCommentArrayList: ArrayList<ContentComment>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val view = inflater.inflate(R.layout.articlecard, null)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        if(holder!=null) {
            holder.card.headline_article.text = articleDataList[position].headline
            holder.card.article_description.text = articleDataList[position].description
            holder.card.article_author_name.text = articleDataList[position].authorName
            Picasso.with(mContext).load(articleDataList[position].urlImage).into(holder.card.article_image)

            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            val CApi = retrofit.create(CommentApi::class.java)
            val call2 = CApi.getCommentCount(articleDataList[position].contentId)

            doAsync {
                call2!!.enqueue(object : Callback<CommentApiResponse> {
                    override fun onResponse(call: Call<CommentApiResponse>, response: Response<CommentApiResponse>) {
                        contentCommentArrayList = response.body()!!.content
                        holder.card.comments.text = contentCommentArrayList!![0].count
                        Log.d("countcomment", contentCommentArrayList!![0].count)
                    }

                    override fun onFailure(call: Call<CommentApiResponse>, t: Throwable) {
                        Log.d("Failed", t.message)
                    }
                })
            }

            if (articleDataList[position].authorImage.isEmpty()) {
                Picasso.with(mContext).load(R.drawable.ic_launcher_background).into(holder.card.article_image)
            } else {
                Picasso.with(mContext).load(articleDataList[position].authorImage).into(holder.card.article_author)
            }
            holder.card.relative_layout.setOnClickListener {
                val i = Intent(mContext, WebViewActivity::class.java)
                i.putExtra("URL", articleDataList[position].slug)
                mContext.startActivity(i)
            }
        }
        else{
            Log.d("ITS NULL","NULL")
        }
    }

    override fun getItemCount(): Int {
        return articleDataList!!.size
    }

    fun addItems(articleData: List<ArticleData>) {
        articleDataList?.addAll(articleData)
        notifyDataSetChanged()
    }

    class ArticleViewHolder(val card : View) : RecyclerView.ViewHolder(card)


    companion object {
        private const val BASE_URL = "https://ign-apis.herokuapp.com/"
    }

}




