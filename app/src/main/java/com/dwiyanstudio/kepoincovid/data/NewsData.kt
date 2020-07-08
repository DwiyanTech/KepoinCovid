package com.dwiyanstudio.kepoincovid.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "News")
data class NewsData(@ColumnInfo(name = "title") var title : String,
                    @ColumnInfo(name = "image_link") var imageLink : String,
                    @ColumnInfo(name = "link") var link : String,
                    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0
)