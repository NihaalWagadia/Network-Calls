package com.example.ign_app.networkcalls

import com.example.ign_app.commentpackage.CommentApiResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CommentApi {
    @Headers("Content-Type: application/json")
    @GET("comments")
     fun getCommentCount(@Query("ids") ids: String): Call<CommentApiResponse>

    companion object {
        const val BASE_URL2 = "https://ign-apis.herokuapp.com/"
    }
}