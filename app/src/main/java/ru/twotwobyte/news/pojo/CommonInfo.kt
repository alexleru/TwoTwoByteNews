package ru.twotwobyte.news.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class CommonInfo(
    @SerializedName("articles")
    @Expose(serialize = false)
    val articles: List<Article>
)
