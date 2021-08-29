package com.test.mvvmtest.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PostTable")
class PostTable(
    val id:String,
    val thumbnail_image:String,
    @PrimaryKey val event_name:String,
    val event_date:String,
    val views:Int,
    val likes:Int,
    val shares:Int
) {
}