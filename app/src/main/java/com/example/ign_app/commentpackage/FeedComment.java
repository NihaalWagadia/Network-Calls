package com.example.ign_app.commentpackage;

import com.example.ign_app.commentpackage.content.ContentComment;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FeedComment {

    @SerializedName("content")
    @Expose
    private ArrayList<ContentComment> content;

    public ArrayList<ContentComment> getContent() {
        return content;
    }

    public void setContent(ArrayList<ContentComment> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "FeedComment{" +
                "content=" + content +
                '}';
    }
}
