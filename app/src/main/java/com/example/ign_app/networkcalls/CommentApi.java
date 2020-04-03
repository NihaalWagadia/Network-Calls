package com.example.ign_app.networkcalls;

import com.example.ign_app.commentpackage.CommentApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface CommentApi {

    String BASE_URL2 = "https://ign-apis.herokuapp.com/";

    @Headers("Content-Type: application/json")
    @GET("comments")
    Call<CommentApiResponse> getCommentCount(@Query("ids") String ids);
}
