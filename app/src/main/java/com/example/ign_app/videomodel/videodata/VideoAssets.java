package com.example.ign_app.videomodel.videodata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoAssets {
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
        return "Assets{" +
                "url='" + url + '\'' +
                '}';
    }
}
