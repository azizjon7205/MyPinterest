package com.example.mypinterest.database

import androidx.room.TypeConverter
import com.example.mypinterest.model.Photo
import com.google.gson.Gson

class PhotoTypeConverter {
    @TypeConverter
    fun fromPhoto(photoItem: Photo): String {
        return Gson().toJson(photoItem)
    }

    @TypeConverter
    fun toPhoto(json: String): Photo {
        return Gson().fromJson(json, Photo::class.java)
    }
}