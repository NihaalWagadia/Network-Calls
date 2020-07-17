package com.example.ign_app.commentpackage

import com.example.ign_app.commentpackage.content.ContentComment
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class CommentApiResponse {


    //lateinit var content: ArrayList<ContentComment>

    @SerializedName("content")
    @Expose
    lateinit var content: ArrayList<ContentComment>

    override fun toString(): String {
        return "FeedComment{" +
                "content=" + content +
                '}'
    }
}