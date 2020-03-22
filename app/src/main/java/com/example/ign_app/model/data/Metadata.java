package com.example.ign_app.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metadata {

    @SerializedName("headline")
    @Expose
    private String headline;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("publishDate")
    @Expose
    private String publishDate;


    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
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
        return "Metadata{" +
                "headline='" + headline + '\'' +
                ", description='" + description + '\'' +
                ", publishDate='" + publishDate + '\'' +
                '}';
    }
}
