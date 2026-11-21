package com.example.soulspaceapp.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.soulspaceapp.repository.AuthRepository
import com.example.soulspaceapp.repository.UserRepository
import com.example.soulspaceapp.viewmodel.AuthViewModel
import com.example.soulspaceapp.viewmodel.UserViewModel

class userFactory(
    private val repository: UserRepository,
    private val authRepository: AuthRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(
                UserViewModel::class.java
            )
        ) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(repository) as T
        }

        if (modelClass.isAssignableFrom(
                AuthViewModel::class.java
            )
        ) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(authRepository) as T
        }
        throw IllegalArgumentException(
            "Unknown View Model"
        )
    }
}
