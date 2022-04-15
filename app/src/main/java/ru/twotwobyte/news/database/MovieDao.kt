package ru.alexleru.movieshustov.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.twotwobyte.news.pojo.Article

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(itemList: List<Article>)

    @Query("DELETE FROM article")
    fun deleteArticles()

    @Query("SELECT * FROM article")
    fun queryArticles(): LiveData<List<Article>>

}