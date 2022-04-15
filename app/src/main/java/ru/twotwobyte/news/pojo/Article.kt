package ru.twotwobyte.news.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "article")
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @SerializedName("title")
    @Expose(serialize = false)
    val title: String,

    @SerializedName("description")
    @Expose(serialize = false)
    val description: String? = null,

    @SerializedName("url")
    @Expose(serialize = false)
    val url: String,

    @SerializedName("urlToImage")
    @Expose(serialize = false)
    val urlToImage: String? = null,

    @SerializedName("publishedAt")
    @Expose(serialize = false)
    val publishedAt: String
)
