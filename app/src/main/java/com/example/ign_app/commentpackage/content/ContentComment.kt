package com.example.ign_app.commentpackage.content

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ContentComment {

    @SerializedName("count")
    @Expose
    lateinit var count: String

    @SerializedName("id")
    @Expose
    var id: String? = null

    override fun toString(): String {
        return "ContentComment{" +
                "count='" + count + '\'' +
                ", id='" + id + '\'' +
                '}'
    }
}