package com.example.mypinterest.utils

import com.example.mypinterest.model.Photo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object Extensions {
    fun parseToString(list: List<Photo>): String{
        val gson = Gson()
        val json = gson.toJson(list)
        return json
    }

    fun parseToList(json: String): ArrayList<Photo>{
        val gson = Gson()
        val type: Type = object : TypeToken<java.util.ArrayList<Photo?>?>() {}.type
        return gson.fromJson(json, type)
    }
}