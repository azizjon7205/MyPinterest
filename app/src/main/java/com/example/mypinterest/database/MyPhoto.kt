package com.example.mypinterest.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mypinterest.model.Urls

@Entity(tableName = "photo_table")
data class MyPhoto(
   @PrimaryKey val id: String,
   val color: String? = null,
   val description: String? = null,
   val urls: String? = null,
   val likes: Int? = null,
   val height: Int? = null,
   val width: Int? = null,
) {
}