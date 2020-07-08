package com.dwiyanstudio.kepoincovid.config

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.*
import androidx.room.Query
import com.dwiyanstudio.kepoincovid.data.NewsData

@Dao
interface NewsDao {
    @Query("select * from News")
    fun getNews() : List<NewsData>

    @Insert(onConflict = REPLACE)
    fun insertNews(insertNews : NewsData)

    @Delete
    fun deleteNews(deleteNews : NewsData)

    @Query("DELETE FROM News")
    fun deleteAllNews()
}