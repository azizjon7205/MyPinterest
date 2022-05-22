package com.example.mypinterest.database

import android.app.Application
import com.example.mypinterest.managers.RoomManager

class PhotoRepository(application: Application) {
    private var pinDao: PinDao? = null

    init {
        RoomManager.init(application)
        this.pinDao = RoomManager.instance!!.pinDao()
    }
}