package com.example.ign_app.articlemodel

import com.example.ign_app.articlemodel.data.Data
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class ArticleApiResponse {

    @SerializedName("data")
    @Expose
    var data: ArrayList<Data>? = null
    override fun toString(): String {
        return "Feed{" +
                "data=" + data +
                '}'
    }
}