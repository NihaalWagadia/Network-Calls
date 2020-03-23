package com.example.ign_app.VideoPackage.VideoData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VideosData {


    @SerializedName("contentId")
    @Expose
    private String contentId;

    @SerializedName("metadata")
    @Expose
    private MetadataVideo metadata;

    @SerializedName("thumbnails")
    @Expose
    private ArrayList<ThumbnailsVideo> thumbnails;

    @SerializedName("assets")
    @Expose
    private ArrayList<Assets> assets;

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public MetadataVideo getMetadata() {
        return metadata;
    }

    public void setMetadata(MetadataVideo metadata) {
        this.metadata = metadata;
    }

    public ArrayList<ThumbnailsVideo> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(ArrayList<ThumbnailsVideo> thumbnails) {
        this.thumbnails = thumbnails;
    }

    public ArrayList<Assets> getAssets() {
        return assets;
    }

    public void setAssets(ArrayList<Assets> assets) {
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
