package com.example.ign_app.videomodel

import com.example.ign_app.videomodel.videodata.VideosData
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class FeedVideo {

    @SerializedName("data")
    @Expose
    var data: ArrayList<VideosData>? = null
    override fun toString(): String {
        return "Feed{" +
                "data=" + data +
                '}'
    }
}