package com.okankkl.currentnews.Repository

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.text.toUpperCase
import androidx.lifecycle.MutableLiveData
import com.okankkl.currentnews.Model.News
import com.okankkl.currentnews.Response.NewsResponse
import com.okankkl.currentnews.Service.NewsApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsApiRepository {


    //var newsList = mutableStateOf(ArrayList<News>())
    var news_list = mutableStateListOf<News>()

    init {
        getApis()
    }

    fun getApis() {

        NewsApiUtils.getNewDao().getAllNews().enqueue(
            object : Callback<NewsResponse> {

                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {

                    try {
                        response.body()?.let {
                            val tempList = it.articles
                            //newsList = mutableStateOf(tempList)
                            //news_list = tempList.toMutableStateList()
                            news_list.clear()
                            for(item in tempList){
                                news_list.add(item)
                            }

                        }
                    }
                    catch (e: Exception){
                        Log.e("errorMsgRepository",e.localizedMessage)
                    }
                }
                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {

                }
            }
        )
    }

    fun apisGet() : SnapshotStateList<News> {
        return news_list
    }

}