package com.example.ign_app.VideoPackage.VideoData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ThumbnailsVideo {

    @SerializedName("url")
    @Expose
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ThumbnailsVideo{" +
                "url='" + url + '\'' +
                '}';
    }
}
