package com.okankkl.currentnews.ViewModel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okankkl.currentnews.Model.News
import com.okankkl.currentnews.Repository.NewsApiRepository
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel(){

    private val apiRepo = NewsApiRepository()

    var news_list = mutableStateListOf<News>()


    init {
        getNews()
        news_list = apiRepo.apisGet()
    }

    private fun getNews(){
        apiRepo.getApis()
    }

}