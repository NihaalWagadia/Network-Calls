package com.example.ign_app;

import com.example.ign_app.model.Feed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface IgnApi {

    String BASE_URL = "https://ign-apis.herokuapp.com/articles/";

    @Headers("Content-Type: application/json")
    @GET("articles?startIndex=30\\count=5")
    Call<Feed> getStuff();
}
