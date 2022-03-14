package com.example.mypinterest.app

import android.app.Application
import com.example.mypinterest.managers.RoomManager

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        RoomManager.init(this)
    }
}