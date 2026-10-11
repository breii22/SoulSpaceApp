package com.example.soulspaceapp.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MoodModel : ViewModel() {
    var mood by mutableStateOf("")
        private set

    fun selectMood(selectedMood: String) {
        mood = selectedMood
    }

    fun clearMood() {
        mood = ""
    }
}

class PetModel : ViewModel() {
    var petName by mutableStateOf("")
        private set

    var petMessage by mutableStateOf("")
        private set

    @JvmName("setPetNameValue")
    fun setPetName(newName: String) {
        if (newName.isNotBlank()) {
            petName = newName
            petMessage = "Nice to meet you!"
        }
    }

    fun getComfortMessage() {
        petMessage = "$petName says: I'm here for you. Take things one step at a time. ♡"
    }

    fun getEncouragingMessage() {
        petMessage = "$petName says: You made it through today. That's something to be proud of! ✨"
    }
}

class RantModel : ViewModel() {
    var rant by mutableStateOf("")
        private set

    var message by mutableStateOf("")
        private set

    fun onRantChange(newRant: String) {
        rant = newRant
    }

    fun saveRant() {
        if (rant.isNotBlank()) {
            message = "Your thoughts have been saved <3"
            rant = ""
        }
    }

    fun clearRant() {
        rant = ""
        message = ""
    }
}
