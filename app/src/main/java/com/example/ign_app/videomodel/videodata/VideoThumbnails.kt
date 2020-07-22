package com.example.ign_app.videomodel.videodata

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VideoThumbnails {
    @SerializedName("url")
    @Expose
    var url: String? = null

    override fun toString(): String {
        return "ThumbnailsVideo{" +
                "url='" + url + '\'' +
                '}'
    }
}