package com.example.ign_app.videomodel.videodata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VideosData {


    @SerializedName("contentId")
    @Expose
    private String contentId;

    @SerializedName("metadata")
    @Expose
    private VideoMetadata metadata;

    @SerializedName("thumbnails")
    @Expose
    private ArrayList<VideoThumbnails> thumbnails;

    @SerializedName("assets")
    @Expose
    private ArrayList<VideoAssets> assets;

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public VideoMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(VideoMetadata metadata) {
        this.metadata = metadata;
    }

    public ArrayList<VideoThumbnails> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(ArrayList<VideoThumbnails> thumbnails) {
        this.thumbnails = thumbnails;
    }

    public ArrayList<VideoAssets> getAssets() {
        return assets;
    }

    public void setAssets(ArrayList<VideoAssets> assets) {
        this.assets = assets;
    }

    @Override
    public String toString() {
        return "VideosData{" +
                "contentId='" + contentId + '\'' +
                ", metadata=" + metadata +
                ", thumbnails=" + thumbnails +
                ", assets=" + assets +
                '}';
    }
}
