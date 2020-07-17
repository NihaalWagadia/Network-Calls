package com.example.ign_app.articlemodel.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Metadata {
    @SerializedName("headline")
    @Expose
    var headline: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("slug")
    @Expose
    var slug: String? = null

    @SerializedName("publishDate")
    @Expose
    var publishDate: String? = null

    override fun toString(): String {
        return "Metadata{" +
                "headline='" + headline + '\'' +
                ", description='" + description + '\'' +
                ", slug='" + slug + '\'' +
                ", publishDate='" + publishDate + '\'' +
                '}'
    }
}