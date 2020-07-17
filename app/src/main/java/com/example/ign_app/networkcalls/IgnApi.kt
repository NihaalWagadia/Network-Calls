package com.example.ign_app.networkcalls

import com.example.ign_app.articlemodel.ArticleApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface IgnApi {
    @Headers("Content-Type: application/json")
    @GET("articles")
    fun getArticleJson(@Query("startIndex") startIndex : Int,
                       @Query("count") count : Int): Call<ArticleApiResponse?>?

    companion object {
        const val BASE_URL = "https://ign-apis.herokuapp.com/"
    }
}