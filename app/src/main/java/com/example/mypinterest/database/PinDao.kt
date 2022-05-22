package com.example.mypinterest.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mypinterest.model.Pin

@Dao
interface PinDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun savePhoto(pin: Pin)

    @Query("SELECT * FROM PINS_TABLE")
    fun getPhotos(): List<Pin>
}