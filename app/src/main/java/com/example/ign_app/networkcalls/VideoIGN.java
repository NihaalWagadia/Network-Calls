package com.example.ign_app.networkcalls;

import com.example.ign_app.videomodel.FeedVideo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface VideoIGN {
    String BASE_URL = "https://ign-apis.herokuapp.com/";

    @Headers("Content-Type: application/json")
    @GET("videos")
    Call<FeedVideo> getVideoData(@Query("startIndex") int startIndex,
                                 @Query("count") int count);
}
