package com.example.soulspaceapp.models

// 1. Main record used by your Repository and Database
data class SoulspaceRecord(
    val id: String = "",
    val username: String = "",
    val password: String = "",
    val mood: String = "",
    val petName: String = "",
    val petMessage: String = "",
    val rant: String = "",
    val profile: UserProfile = UserProfile()
)

// 2. Personal profile details captured during registration
data class UserProfile(
    val firstName: String = "",
    val middleName: String = "",
    val lastName: String = "",
    val age: String = "",
    val gender: String = ""
)

// 3. User mood log data class
data class MoodRecord(
    val mood: String = "",
    val timestamp: Long = System.currentTimeMillis()
)

// 4. Virtual pet companion data class
data class PetRecord(
    val petName: String = "",
    val petMessage: String = ""
)

// 5. Journal and rant history data class
data class RantRecord(
    val rantText: String = "",
    val timestamp: Long = System.currentTimeMillis()
)