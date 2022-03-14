package com.example.mypinterest.database

import android.app.Application
import com.example.mypinterest.managers.RoomManager

class PhotoRepository(application: Application) {
    private var photoDao: PhotoDao? = null

    init {
//        RoomManager.init(application)
        this.photoDao = RoomManager.instance!!.photoDao()
    }
}