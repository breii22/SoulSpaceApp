package com.example.soulspaceapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.soulspaceapp.viewmodel.AuthViewModel
import com.example.soulspaceapp.viewmodel.UserViewModel
import com.example.soulspaceapp.models.userFactory
import com.example.soulspaceapp.repository.AuthRepository
import com.example.soulspaceapp.repository.UserRepository
import com.example.soulspaceapp.screens.DashboardScreen
import com.example.soulspaceapp.screens.HistoryScreen
import com.example.soulspaceapp.screens.HomeScreen
import com.example.soulspaceapp.screens.LoginScreen
import com.example.soulspaceapp.screens.MoodScreen
import com.example.soulspaceapp.screens.PetScreen
import com.example.soulspaceapp.screens.ProfileScreen
import com.example.soulspaceapp.screens.RantScreen
import com.example.soulspaceapp.screens.RegisterScreen
import com.example.soulspaceapp.screens.SplashScreen

@Composable
fun AppNavigation() {
    val navrouting = rememberNavController()
    val repository = UserRepository()
    val authrepo = AuthRepository()

    val userViewModel: UserViewModel = viewModel(
        factory = userFactory(repository, authrepo)
    )

    val authViewModel: AuthViewModel = viewModel(
        factory = userFactory(repository, authrepo)
    )

    NavHost(
        navController = navrouting,
        startDestination = "splash"
    ) {
        composable(route = "dashboard") {
            DashboardScreen(navController = navrouting, viewModel = userViewModel)
        }

        composable(route = "mood") {
            MoodScreen(navController = navrouting, viewModel = userViewModel)
        }

        composable(route = "rant") {
            RantScreen(navController = navrouting, viewModel = userViewModel)
        }

        composable(route = "pet") {
            PetScreen(navController = navrouting, viewModel = userViewModel)
        }

        composable(route = "history") {
            HistoryScreen(navController = navrouting, viewModel = userViewModel)
        }

        composable(route = "profile") {
            ProfileScreen(navController = navrouting, viewModel = userViewModel)
        }

        composable(route = "menu") {
            HomeScreen(navController = navrouting, viewModel = userViewModel)
        }

        composable(route = "register") {
            RegisterScreen(navController = navrouting, viewModel = authViewModel)
        }

        composable(route = "login") {
            LoginScreen(navController = navrouting, viewModel = authViewModel)
        }

        composable(route = "splash") {
            SplashScreen(navController = navrouting, viewModel = authViewModel)
        }
    }
}