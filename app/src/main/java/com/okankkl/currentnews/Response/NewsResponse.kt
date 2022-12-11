package com.okankkl.currentnews.Response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.okankkl.currentnews.Model.News

data class NewsResponse (

    @SerializedName("status") @Expose var status : String,

    @SerializedName("totalResults") @Expose var totalResults : Int,

    @SerializedName("articles") @Expose var articles : List<News>


)