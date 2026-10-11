package com.example.soulspaceapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.soulspaceapp.models.UserViewModel
import com.example.soulspaceapp.models.userFactory
import com.example.soulspaceapp.models.userRepository
import com.example.soulspaceapp.screens.HomeScreen
import com.example.soulspaceapp.screens.LoginScreen

@Composable
fun AppNavigation(
    repository: userRepository
) {
    val navController = rememberNavController()

    // Create the shared UserViewModel instance using your repository factory
    val userViewModel: UserViewModel = viewModel(
        factory = userFactory(repository)
    )

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable(route = "login") {
            LoginScreen(
                navController = navController,
                viewModel = userViewModel
            )
        }

        composable(route = "home") {
            HomeScreen(
                navController = navController,
                viewModel = userViewModel
            )
        }
    }
}