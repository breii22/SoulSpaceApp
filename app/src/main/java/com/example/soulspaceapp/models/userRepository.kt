package com.example.soulspaceapp.models

import kotlinx.coroutines.flow.Flow

class userRepository(
    private val userDao: userDao
) {
    val usrList: Flow<List<systemrecord>> = userDao.getAllUsers()
    val emplist: Flow<List<systemrecord>> get() = usrList

    suspend fun insert(emp: systemrecord) {
        userDao.insert(emp)
    }

    suspend fun update(emp: systemrecord) {
        userDao.update(emp)
    }

    suspend fun delete(emp: systemrecord) {
        userDao.delete(emp)
    }
}
