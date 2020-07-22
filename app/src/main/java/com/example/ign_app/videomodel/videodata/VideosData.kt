package com.example.ign_app.videomodel.videodata

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class VideosData {
    @SerializedName("contentId")
    @Expose
    var contentId: String? = null

    @SerializedName("metadata")
    @Expose
    var metadata: VideoMetadata? = null

    @SerializedName("thumbnails")
    @Expose
    var thumbnails: ArrayList<VideoThumbnails>? = null

    @SerializedName("assets")
    @Expose
    var assets: ArrayList<VideoAssets>? = null

    override fun toString(): String {
        return "VideosData{" +
                "contentId='" + contentId + '\'' +
                ", metadata=" + metadata +
                ", thumbnails=" + thumbnails +
                ", assets=" + assets +
                '}'
    }
}