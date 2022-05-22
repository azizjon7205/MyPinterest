package com.example.mypinterest.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pins_table")
data class Pin(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val photo: Photo
)
