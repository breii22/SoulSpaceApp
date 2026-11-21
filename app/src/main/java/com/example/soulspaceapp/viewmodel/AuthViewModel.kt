package com.example.soulspaceapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.soulspaceapp.repository.AuthRepository

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    fun registration(
        email: String,
        password: String,
        onResult: (Boolean, String) -> Unit
    ) {
        repository.registrationFunc(email, password) { success, result ->
            onResult(success, result)
        }
    }

    fun login(
        email: String,
        password: String,
        onResult: (Boolean, String) -> Unit
    ) {
        repository.loginFunc(email, password) { success, result ->
            onResult(success, result)
        }
    }

    fun isLogin(): Boolean {
        return repository.isLogin()
    }
}