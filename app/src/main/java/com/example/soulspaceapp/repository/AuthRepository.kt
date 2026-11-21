package com.example.soulspaceapp.repository

import com.google.firebase.auth.FirebaseAuth

class AuthRepository {

    private val auth = FirebaseAuth.getInstance();

    fun registrationFunc (
        email: String,
        password: String,
        onResult: (Boolean, String) -> Unit
    ){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { result ->

                if(result.isSuccessful) {
                    onResult(true, "Registration Successful")
                }else {
                    onResult(false, result.exception?.message ?: "Registration Failed")
                }
            }
    }

    fun loginFunc (
        email: String,
        password: String,
        onResult: (Boolean, String) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    onResult(true, "Login Successful")
                } else {
                    onResult(false, result.exception?.message ?: "Login Failed")
                }

            }
    }

    fun logoutFunc()
    {
     auth.signOut();
    }

    fun getLoginUser() : String? {
        return auth.currentUser?.email;
    }

    fun isLogin() : Boolean {
        return auth.currentUser != null;
    }
}
