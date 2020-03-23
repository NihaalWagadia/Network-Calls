package com.example.ign_app.VideoPackage.VideoData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MetadataVideo {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("publishDate")
    @Expose
    private String publishDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "MetadataVideo{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", publishDate='" + publishDate + '\'' +
                '}';
    }
}
