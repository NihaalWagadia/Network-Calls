package com.example.ign_app.VideoPackage;

import com.example.ign_app.VideoPackage.VideoData.VideosData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FeedVideo {

    public ArrayList<VideosData> getData() {
        return data;
    }

    public void setData(ArrayList<VideosData> data) {
        this.data = data;
    }

    @SerializedName("data")
    @Expose
    private ArrayList<VideosData> data;

    @Override
    public String toString() {
        return "Feed{" +
                "data=" + data +
                '}';
    }
}
