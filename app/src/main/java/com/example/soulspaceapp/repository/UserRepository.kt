package com.example.soulspaceapp.repository

import android.util.Log
import com.example.soulspaceapp.models.SoulspaceRecord
import com.example.soulspaceapp.models.UserProfile
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.SetOptions

class UserRepository(
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) {

    private fun toMap(usr: SoulspaceRecord) = hashMapOf(
        "username" to usr.username,
        "password" to usr.password,
        "mood" to usr.mood,
        "petName" to usr.petName,
        "petMessage" to usr.petMessage,
        "rant" to usr.rant,
        "profile" to hashMapOf(
            "firstName" to usr.profile.firstName,
            "middleName" to usr.profile.middleName,
            "lastName" to usr.profile.lastName,
            "age" to usr.profile.age,
            "gender" to usr.profile.gender
        )
    )

    fun insert(usr: SoulspaceRecord) {
        db.collection("coll_soulspace")
            .add(toMap(usr))
            .addOnSuccessListener { docRef ->
                Log.d("SoulSpace", "Record saved properly: ${docRef.id}")
            }
            .addOnFailureListener { error ->
                Log.e("SoulSpace", "Registration Failed: ${error.message}", error)
            }
    }

    fun getUser(onResult: (List<SoulspaceRecord>) -> Unit): ListenerRegistration {
        return db.collection("coll_soulspace")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.e("SoulSpace", "Listen failed: ${error.message}", error)
                    onResult(emptyList())
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val usr = snapshot.documents.map { document ->
                        val profileMap = document.get("profile") as? Map<*, *>
                        val userProfile = UserProfile(
                            firstName = profileMap?.get("firstName") as? String ?: "",
                            middleName = profileMap?.get("middleName") as? String ?: "",
                            lastName = profileMap?.get("lastName") as? String ?: "",
                            age = profileMap?.get("age") as? String ?: "",
                            gender = profileMap?.get("gender") as? String ?: ""
                        )

                        SoulspaceRecord(
                            id = document.id,
                            username = document.getString("username") ?: "",
                            password = document.getString("password") ?: "",
                            mood = document.getString("mood") ?: "",
                            petName = document.getString("petName") ?: "",
                            petMessage = document.getString("petMessage") ?: "",
                            rant = document.getString("rant") ?: "",
                            profile = userProfile
                        )
                    }
                    onResult(usr)
                }
            }
    }

    fun get(onResult: (List<SoulspaceRecord>) -> Unit): ListenerRegistration {
        return getUser(onResult)
    }

    fun update(usr: SoulspaceRecord) {
        if (usr.id.isBlank()) {
            Log.e("SoulSpace", "Update skipped: record has no id")
            return
        }
        db.collection("coll_soulspace")
            .document(usr.id)
            .set(toMap(usr), SetOptions.merge())
            .addOnSuccessListener {
                Log.d("SoulSpace", "Record updated properly: ${usr.id}")
            }
            .addOnFailureListener { error ->
                Log.e("SoulSpace", "Update failed: ${usr.id}", error)
            }
    }

    fun delete(usr: SoulspaceRecord) {
        if (usr.id.isNotBlank()) {
            db.collection("coll_soulspace")
                .document(usr.id)
                .delete()
                .addOnSuccessListener {
                    Log.d("SoulSpace", "Record deleted properly: ${usr.id}")
                }
                .addOnFailureListener { error ->
                    Log.e("SoulSpace", "Delete failed: ${usr.id}", error)
                }
        }
    }
}