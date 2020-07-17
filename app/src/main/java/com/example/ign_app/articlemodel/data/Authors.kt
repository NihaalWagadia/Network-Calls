package com.example.ign_app.articlemodel.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

 class Authors {

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("thumbnail")
    @Expose
     var thumbnail:String? = null

    override fun toString():String {
        return "Authors{" +
                "name='" + name + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }
}
