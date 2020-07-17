package com.example.ign_app.articlemodel.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Thumbnails {

    @SerializedName("url")
    @Expose
    var url:String? = null

    @SerializedName("size")
    @Expose
    var size:String? = null

    @SerializedName("width")
    @Expose
    var width:String? = null

    @SerializedName("height")
    @Expose
    var height:String?=null

    override fun toString():String {
        return "Thumbnails{" +
                "url='" + url + '\'' +
                ", size='" + size + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                '}';
    }
}
