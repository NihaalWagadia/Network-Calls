package com.example.ign_app;

import com.example.ign_app.VideoPackage.FeedVideo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface VideoIGN {
    String BASE_URL = "https://ign-apis.herokuapp.com/videos/";

    @Headers("Content-Type: application/json")
    @GET("videos?startIndex=30\\u0026count=5")
    Call<FeedVideo> getStuff();
}
