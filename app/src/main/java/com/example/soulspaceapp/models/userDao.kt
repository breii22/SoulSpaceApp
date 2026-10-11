package com.example.soulspaceapp.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface userDao {

    @Query("SELECT * FROM systemrecord")
    fun getAllUsers(): Flow<List<systemrecord>>

    @Query("SELECT * FROM systemrecord WHERE id = :id")
    fun getUserById(id: Int): Flow<systemrecord?>

    @Insert
    suspend fun insert(emp: systemrecord)

    @Update
    suspend fun update(emp: systemrecord)

    @Delete
    suspend fun delete(emp: systemrecord)
}