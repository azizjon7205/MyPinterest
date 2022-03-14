package com.example.mypinterest.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun savePhoto(photo: MyPhoto)

    @Query("SELECT * FROM photo_table")
    fun getPhotos(): List<MyPhoto>
}