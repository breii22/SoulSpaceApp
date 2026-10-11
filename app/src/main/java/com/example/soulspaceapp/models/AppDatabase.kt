package com.example.soulspaceapp.models

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [systemrecord::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun usrdao() : userDao
}

