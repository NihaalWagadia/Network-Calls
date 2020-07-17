package com.example.ign_app.articlemodel.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class Data {


    @SerializedName("contentId")
    @Expose
    var contentId: String? = null

    @SerializedName("metadata")
    @Expose
    var metadata: Metadata? =null

    @SerializedName("thumbnails")
    @Expose
    var thumbnails: ArrayList<Thumbnails>? = null

    @SerializedName("authors")
    @Expose
    var authors: ArrayList<Authors>?=null


    override fun toString(): String {
        return "com.example.ign_app.articlemodel.data.Data{" +
                "contentId='" + contentId + '\'' +
                ", metadata=" + metadata +
                ", thumbnails=" + thumbnails +
                ", authors=" + authors +
                '}';
    }
}