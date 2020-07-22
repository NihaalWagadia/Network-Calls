package com.example.ign_app.videomodel.videodata

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VideoMetadata {
    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("publishDate")
    @Expose
    var publishDate: String? = null

    override fun toString(): String {
        return "MetadataVideo{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", publishDate='" + publishDate + '\'' +
                '}'
    }
}