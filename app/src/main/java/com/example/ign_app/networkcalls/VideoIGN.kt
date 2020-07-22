package com.example.ign_app.networkcalls

import com.example.ign_app.videomodel.FeedVideo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface VideoIGN {
    @Headers("Content-Type: application/json")
    @GET("videos")
    fun getVideoData(@Query("startIndex") startIndex: Int,
                     @Query("count") count: Int): Call<FeedVideo?>?

    companion object {
        const val BASE_URL = "https://ign-apis.herokuapp.com/"
    }
}