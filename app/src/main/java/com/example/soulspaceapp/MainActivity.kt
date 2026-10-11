package com.example.soulspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.soulspaceapp.navigation.AppNavigation
import com.example.soulspaceapp.ui.theme.SoulSpaceAppTheme
import androidx.activity.enableEdgeToEdge

import com.example.soulspaceapp.models.userApplication

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val usr = application as userApplication

        setContent {
            SoulSpaceAppTheme {
                AppNavigation(usr.usrRepository)
            }
        }
    }
}