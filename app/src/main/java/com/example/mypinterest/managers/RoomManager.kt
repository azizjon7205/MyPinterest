package com.example.mypinterest.managers

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mypinterest.database.PhotoTypeConverter
import com.example.mypinterest.database.PinDao
import com.example.mypinterest.model.Pin

@Database(entities = [Pin::class], version = 1, exportSchema = false)
@TypeConverters(PhotoTypeConverter::class)
abstract class RoomManager: RoomDatabase() {

    abstract fun pinDao(): PinDao

    companion object{
        var instance: RoomManager? = null

        @Synchronized
        fun init(context: Context){
            if (instance == null){
                instance = Room.databaseBuilder(context, RoomManager::class.java, "photos_database.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }

        }
    }
}