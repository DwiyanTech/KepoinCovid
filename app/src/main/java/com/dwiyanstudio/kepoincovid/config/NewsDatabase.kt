package com.dwiyanstudio.kepoincovid.config

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dwiyanstudio.kepoincovid.data.NewsData

@Database(entities = arrayOf(NewsData::class),version = 1)
abstract class NewsDatabase : RoomDatabase(){

    abstract fun newsDao() : NewsDao

    companion object {
        private var newsBuild : NewsDatabase ? = null

        fun  getNewsBuild(context: Context) : NewsDatabase?{
            if(newsBuild == null){
                synchronized(NewsDatabase::class){
                    newsBuild = Room.databaseBuilder(context.applicationContext,NewsDatabase::class.java ,"savenews.db").build()
                }
            }
            return newsBuild
        }
    }


}