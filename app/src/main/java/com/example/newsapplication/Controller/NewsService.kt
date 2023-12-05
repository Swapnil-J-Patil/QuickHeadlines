package com.example.newsapplication.Controller

import com.example.newsapplication.Model.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines")
    fun getNews(
        @Query("country") country:String,
        @Query("category") category:String,
        @Query("apiKey") apiKey: String
    ): Call<Data>
}
