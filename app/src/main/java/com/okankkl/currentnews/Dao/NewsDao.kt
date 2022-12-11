package com.okankkl.currentnews.Dao

import com.okankkl.currentnews.Response.NewsResponse
import okhttp3.Response
import retrofit2.Call
import retrofit2.http.GET

interface NewsDao {

    @GET("top-headlines?country=tr&apiKey=48c0c9d98dac4cd38db4c9eb42cedcea")
    fun getAllNews() : Call<NewsResponse>
}