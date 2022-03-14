package com.example.mypinterest.managers

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class PrefsManager private constructor(context: Context) {
    private var sharedPreferences: SharedPreferences? =
        context.getSharedPreferences("NotePref", Context.MODE_PRIVATE)

    companion object {
         var instance: PrefsManager? = null

        fun init(context: Context) {
            if (instance == null)
                instance = PrefsManager(context)
        }
    }

    fun saveArrayList(key: String, list: ArrayList<String>) {
        val prefsEditor = sharedPreferences!!.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        prefsEditor.putString(key, json)
        prefsEditor.commit()
    }

    fun getArrayList(key: String): ArrayList<String>?{
        val gson = Gson()
        val json: String? = sharedPreferences!!.getString(key, "")
        val type: Type = object : TypeToken<java.util.ArrayList<String?>?>() {}.getType()
        return gson.fromJson(json, type)
    }

}