package com.okankkl.currentnews.Model

import androidx.compose.runtime.MutableState
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class News (

    @SerializedName("source") @Expose var source : HashMap<String,String>?,
    @SerializedName("author") @Expose var author : String,
    @SerializedName("title") @Expose var title : String,
    @SerializedName("description") @Expose var description : String,
    @SerializedName("url") @Expose var url : String,
    @SerializedName("urlToImage") @Expose var urlToImage : String,
    @SerializedName("publishedAt") @Expose var publishedAt : String,
    @SerializedName("content") @Expose var content : String

) : java.io.Serializable