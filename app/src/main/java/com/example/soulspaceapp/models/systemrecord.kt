package com.example.soulspaceapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class systemrecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val usrname: String = "",
    val mood: String = "",
    val petName: String = "",
    val petMessage: String = "",
    val message: String = "",
    val rant: String = "",

    //sample change in my model
)