package com.example.soulspaceapp.models


import android.app.Application
import androidx.room.Room

class userApplication : Application() {

    val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            klass = AppDatabase::class.java,
            name = "usr_database"
        ).build()
    }

    val usrRepository by lazy {
        userRepository(userDao = database.usrdao())
    }
}