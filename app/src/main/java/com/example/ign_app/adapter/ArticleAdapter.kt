package com.example.ign_app.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ign_app.R
import com.example.ign_app.activities.WebViewActivity
import com.example.ign_app.adapter.ArticleAdapter.ArticleViewHolder
import com.example.ign_app.commentpackage.CommentApiResponse
import com.example.ign_app.commentpackage.content.ContentComment
import com.example.ign_app.helper.ArticleData
import com.example.ign_app.helper.CommentData
import com.example.ign_app.networkcalls.CommentApi
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class ArticleAdapter //    this.commentDataList = commentDataList;
(private val mContext: Context, private val articleDataList: MutableList<ArticleData>) : RecyclerView.Adapter<ArticleViewHolder>() {
    var commentDataList: List<CommentData>? = null
    var contentCommentArrayList: ArrayList<ContentComment>? = null
    private val isLoaderVisible = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val view = inflater.inflate(R.layout.articlecard, null)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        var (contentId, headline, description, urlImage, authorName, authorImage, slug) = articleDataList[position]
        holder.head_txt.text = headline
        holder.des_txt.text = description
        holder.authorName_txt.text = authorName
        Picasso.with(mContext).load(urlImage).into(holder.article_image)
        var retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        var CApi = retrofit.create(CommentApi::class.java)
        var call2 = CApi.getCommentCount(contentId)
        call2!!.enqueue(object : Callback<CommentApiResponse> {
            override fun onResponse(call: Call<CommentApiResponse>, response: Response<CommentApiResponse>) {
                contentCommentArrayList = response.body()!!.content
                holder.comments.text = contentCommentArrayList!![0].count
            }

            override fun onFailure(call: Call<CommentApiResponse>, t: Throwable) {}
        })
        if (authorImage.isEmpty()) {
            Picasso.with(mContext).load(R.drawable.ic_launcher_background).into(holder.author_image)
        } else {
            Picasso.with(mContext).load(authorImage).into(holder.author_image)
        }
        holder.relativeLayout.setOnClickListener {
            val i = Intent(mContext, WebViewActivity::class.java)
            i.putExtra("URL", slug)
            mContext.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return articleDataList.size
    }

    fun addItems(articleData: MutableCollection<ArticleData?>?) {
        articleDataList.addAll(articleData)
        notifyDataSetChanged()
    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var head_txt: TextView
        var des_txt: TextView
        var authorName_txt: TextView
        var comments: TextView
        var article_image: ImageView
        var author_image: CircleImageView
        var relativeLayout: RelativeLayout
        var webView: WebView? = null

        init {
            head_txt = itemView.findViewById(R.id.headline_article)
            des_txt = itemView.findViewById(R.id.article_description)
            authorName_txt = itemView.findViewById(R.id.article_author_name)
            article_image = itemView.findViewById(R.id.article_image)
            author_image = itemView.findViewById(R.id.article_author)
            comments = itemView.findViewById(R.id.comments)
            relativeLayout = itemView.findViewById(R.id.relative_layout)
        }
    }

    companion object {
        private const val BASE_URL = "https://ign-apis.herokuapp.com/"
        private const val VIEW_TYPE_LOADING = 0
        private const val VIEW_TYPE_NORMAL = 1
    }

}

private fun <T> Call<T>.enqueue(callback: Callback<CommentApiResponse>) {

}

