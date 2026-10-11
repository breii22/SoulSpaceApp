package com.example.soulspaceapp.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: userRepository
) : ViewModel() {

    val usrList: Flow<List<systemrecord>> = userRepository.usrList

    fun insertUser(username: String) {
        if (username.isNotBlank()) {
            viewModelScope.launch {
                userRepository.insert(
                    systemrecord(
                        usrname = username
                    )
                )
            }
        }
    }

    fun deleteUser(userid: Int) {
        viewModelScope.launch {
            val usr = usrList.first().find {
                it.id == userid
            }

            usr?.let {
                userRepository.delete(it)
            }
        }
    }

    private val _mood = MutableStateFlow("")
    val mood: StateFlow<String> = _mood.asStateFlow()

    fun selectMood(selectedMood: String) {
        viewModelScope.launch {
            _mood.value = selectedMood
            userRepository.insert(
                systemrecord(
                    mood = selectedMood
                )
            )
        }
    }

    fun clearMood() {
        viewModelScope.launch {
            _mood.value = ""
        }
    }

    private val _petName = MutableStateFlow("")
    val petName: StateFlow<String> = _petName.asStateFlow()

    private val _petMessage = MutableStateFlow("")
    val petMessage: StateFlow<String> = _petMessage.asStateFlow()

    fun setPetName(newName: String) {
        if (newName.isNotBlank()) {
            viewModelScope.launch {
                _petName.value = newName
                _petMessage.value = "Nice to meet you!"
            }
        }
    }

    fun getComfortMessage() {
        viewModelScope.launch {
            _petMessage.value =
                "${_petName.value} says: I'm here for you. Take things one step at a time. ♡"
        }
    }

    fun getEncouragingMessage() {
        viewModelScope.launch {
            _petMessage.value =
                "${_petName.value} says: You made it through today. That's something to be proud of! ✨"
        }
    }

    private val _rant = MutableStateFlow("")
    val rant: StateFlow<String> = _rant.asStateFlow()

    private val _message = MutableStateFlow("")
    val message: StateFlow<String> = _message.asStateFlow()

    fun onRantChange(newRant: String) {
        _rant.value = newRant
    }

    fun saveRant() {
        if (_rant.value.isNotBlank()) {
            viewModelScope.launch {
                _message.value = "Your thoughts have been saved <3"
                _rant.value = ""
            }
        }
    }

    fun clearRant() {
        viewModelScope.launch {
            _rant.value = ""
            _message.value = ""
        }
    }
}