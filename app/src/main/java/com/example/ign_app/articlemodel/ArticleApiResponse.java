package com.example.ign_app.articlemodel;

import com.example.ign_app.articlemodel.data.Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ArticleApiResponse {

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    @SerializedName("data")
    @Expose
    private ArrayList<Data> data;

    @Override
    public String toString() {
        return "Feed{" +
                "data=" + data +
                '}';
    }
}
