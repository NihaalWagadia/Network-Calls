package com.example.ign_app.videomodel.videodata

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VideoAssets {
    @SerializedName("url")
    @Expose
    var url: String? = null

    override fun toString(): String {
        return "Assets{" +
                "url='" + url + '\'' +
                '}'
    }
}