package com.example.ign_app.commentpackage.content;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentComment {

    @SerializedName("count")
    @Expose
    private String count;

    @SerializedName("id")
    @Expose
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ContentComment{" +
                "count='" + count + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
