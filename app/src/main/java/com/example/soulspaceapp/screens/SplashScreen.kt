package com.example.soulspaceapp.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.example.soulspaceapp.viewmodel.AuthViewModel

@Composable
fun SplashScreen(
    navController: NavHostController,
    viewModel: AuthViewModel
) {
    LaunchedEffect(key1 = Unit) {
        if (viewModel.isLogin()) {
            navController.navigate( route = "dashboard")
        } else {
            navController.navigate( route = "login")
        }
    }
}