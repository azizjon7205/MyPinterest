package com.example.mypinterest.managers

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mypinterest.database.MyPhoto
import com.example.mypinterest.database.PhotoDao

@Database(entities = [MyPhoto::class], version = 1)
abstract class RoomManager: RoomDatabase() {

    abstract fun photoDao(): PhotoDao

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