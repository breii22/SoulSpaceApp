package com.example.soulspaceapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.soulspaceapp.models.SoulspaceRecord
import com.example.soulspaceapp.models.UserProfile
import com.example.soulspaceapp.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _usrList = MutableStateFlow<List<SoulspaceRecord>>(emptyList())
    val usrList: StateFlow<List<SoulspaceRecord>> = _usrList.asStateFlow()

    // Tracks the currently logged-in user's username
    private val _currentUsername = MutableStateFlow("")
    val currentUsername: StateFlow<String> = _currentUsername.asStateFlow()

    private val _mood = MutableStateFlow("")
    val mood: StateFlow<String> = _mood.asStateFlow()

    private val _petName = MutableStateFlow("")
    val petName: StateFlow<String> = _petName.asStateFlow()

    private val _petMessage = MutableStateFlow("")
    val petMessage: StateFlow<String> = _petMessage.asStateFlow()

    private val _rant = MutableStateFlow("")
    val rant: StateFlow<String> = _rant.asStateFlow()

    private val _message = MutableStateFlow("")
    val message: StateFlow<String> = _message.asStateFlow()

    init {
        getUser()
    }

    // Helper to find the active user record based on login state
    private fun getActiveUser(): SoulspaceRecord? {
        val list = _usrList.value
        return list.find { it.username.equals(_currentUsername.value, ignoreCase = true) }
            ?: list.lastOrNull()
    }

    fun setCurrentUser(username: String) {
        _currentUsername.value = username
        val matchedUser = _usrList.value.find { it.username.equals(username, ignoreCase = true) }
        if (matchedUser != null) {
            _mood.value = matchedUser.mood
            _petName.value = matchedUser.petName
            _petMessage.value = matchedUser.petMessage
            _rant.value = matchedUser.rant
        } else {
            // Reset states for a brand new user session
            _mood.value = ""
            _petName.value = ""
            _petMessage.value = ""
            _rant.value = ""
        }
    }

    fun getUser() {
        userRepository.getUser { records ->
            _usrList.value = records
            val currentUser = getActiveUser()
            if (currentUser != null) {
                if (currentUser.mood.isNotEmpty()) _mood.value = currentUser.mood
                if (currentUser.petName.isNotEmpty()) _petName.value = currentUser.petName
                if (currentUser.petMessage.isNotEmpty()) _petMessage.value = currentUser.petMessage
                if (currentUser.rant.isNotEmpty()) _rant.value = currentUser.rant
            }
        }
    }

    fun insertUser(
        username: String,
        password: String = "",
        firstName: String = "",
        middleName: String = "",
        lastName: String = "",
        age: String = "",
        gender: String = ""
    ) {
        val newUser = SoulspaceRecord(
            username = username,
            password = password,
            mood = _mood.value,
            petName = _petName.value,
            petMessage = _petMessage.value,
            rant = _rant.value,
            profile = UserProfile(
                firstName = firstName,
                middleName = middleName,
                lastName = lastName,
                age = age,
                gender = gender
            )
        )
        userRepository.insert(newUser)
        setCurrentUser(username)
    }

    fun selectMood(selectedMood: String) {
        _mood.value = selectedMood
        val currentUser = getActiveUser()
        if (currentUser != null && currentUser.id.isNotBlank()) {
            userRepository.update(currentUser.copy(mood = selectedMood))
        }
    }

    fun setPetName(name: String) {
        _petName.value = name
        val currentUser = getActiveUser()
        if (currentUser != null && currentUser.id.isNotBlank()) {
            userRepository.update(currentUser.copy(petName = name))
        }
    }

    fun onRantChange(text: String) {
        _rant.value = text
    }

    fun saveRant() {
        val currentRant = _rant.value
        if (currentRant.isNotBlank()) {
            val currentUser = getActiveUser()
            if (currentUser != null && currentUser.id.isNotBlank()) {
                userRepository.update(currentUser.copy(rant = currentRant))
            } else {
                userRepository.insert(
                    SoulspaceRecord(
                        username = _currentUsername.value,
                        rant = currentRant
                    )
                )
            }
            _message.value = "Your rant was saved successfully! ♡"
        } else {
            _message.value = "Please write something before saving."
        }
    }

    fun getComfortMessage() {
        val pet = _petName.value.ifEmpty { "Your pet" }
        val comfortMessages = listOf(
            "Take a deep breath. $pet is right here with you.",
            "It's okay to feel overwhelmed. $pet gives you a soft cuddle.",
            "You don't have to carry everything alone. $pet is listening.",
            "Sending you warmth and love. $pet rests beside you softly."
        )
        val msg = comfortMessages.random()
        _petMessage.value = msg
        val currentUser = getActiveUser()
        if (currentUser != null && currentUser.id.isNotBlank()) {
            userRepository.update(currentUser.copy(petMessage = msg))
        }
    }

    fun getEncouragingMessage() {
        val pet = _petName.value.ifEmpty { "Your pet" }
        val encouragingMessages = listOf(
            "$pet says: You are doing amazing! Keep going!",
            "$pet believes in you! You've got this!",
            "$pet wags tail happily: I'm so proud of you today!",
            "$pet cheers for you: Every small step counts!"
        )
        val msg = encouragingMessages.random()
        _petMessage.value = msg
        val currentUser = getActiveUser()
        if (currentUser != null && currentUser.id.isNotBlank()) {
            userRepository.update(currentUser.copy(petMessage = msg))
        }
    }
}