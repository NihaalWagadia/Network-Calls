package com.example.ign_app.articlemodel.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Data {


    @SerializedName("contentId")
    @Expose
    private String contentId;

    @SerializedName("metadata")
    @Expose
    private  Metadata metadata;

    @SerializedName("thumbnails")
    @Expose
    private ArrayList<Thumbnails> thumbnails;

    @SerializedName("authors")
    @Expose
    private  ArrayList<Authors> authors;

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public ArrayList<Thumbnails> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(ArrayList<Thumbnails> thumbnails) {
        this.thumbnails = thumbnails;
    }

    public ArrayList<Authors> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Authors> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Data{" +
                "contentId='" + contentId + '\'' +
                ", metadata=" + metadata +
                ", thumbnails=" + thumbnails +
                ", authors=" + authors +
                '}';
    }
}
