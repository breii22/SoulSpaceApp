package com.example.soulspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.soulspaceapp.repository.UserRepository
import com.example.soulspaceapp.navigation.AppNavigation
import com.example.soulspaceapp.ui.theme.SoulSpaceAppTheme
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val repository = UserRepository()

        setContent {
            SoulSpaceAppTheme {
                AppNavigation()
            }
        }
    }
}