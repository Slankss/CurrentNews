package com.okankkl.currentnews.Service

import com.okankkl.currentnews.Dao.NewsDao

class NewsApiUtils {

    companion object{

        private const val BASE_URL = "https://newsapi.org/v2/"

        fun getNewDao() : NewsDao {
            return RetrofitClient.getClient(BASE_URL).create(NewsDao::class.java)
        }

    }
}