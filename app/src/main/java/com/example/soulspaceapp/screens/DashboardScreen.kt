package com.example.soulspaceapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.soulspaceapp.viewmodel.UserViewModel

@Composable
fun DashboardScreen(
    navController: NavHostController,
    viewModel: UserViewModel
) {
    val currentUsername by viewModel.currentUsername.collectAsState()
    val usrList by viewModel.usrList.collectAsState()

    val currentUser = usrList.find { it.username.equals(currentUsername, ignoreCase = true) }
        ?: usrList.lastOrNull()
    val displayName = currentUser?.profile?.firstName?.takeIf { it.isNotBlank() } ?: currentUsername.ifBlank { "Friend" }

    // Aesthetic Pink & Lavender Palette
    val primaryPink = Color(0xFFD87093)
    val textDark = Color(0xFF4A3B45)
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFEEF2), // Soft blush pink
                        Color(0xFFF3E8FF)  // Soft warm lavender
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Welcome, $displayName ✨",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = textDark
            )
            Text(
                text = "Choose your sanctuary activity",
                fontSize = 13.sp,
                color = textDark.copy(alpha = 0.6f)
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Navigation Buttons Grid / List
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                DashboardCard(title = "😊 Mood Tracker", description = "Log and reflect on your feelings") {
                    navController.navigate("mood")
                }
                DashboardCard(title = "📝 Rant Dump", description = "Safely unload your thoughts") {
                    navController.navigate("rant")
                }
                DashboardCard(title = "🐾 Pet Companion", description = "Visit your emotional support buddy") {
                    navController.navigate("pet")
                }
                DashboardCard(title = "📜 Rant History", description = "Review your past entries") {
                    navController.navigate("history")
                }
                DashboardCard(title = "👤 My Profile", description = "View your account details") {
                    navController.navigate("profile")
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Log Out Button at Bottom Right / Full Width Option
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = {
                        navController.navigate("login") {
                            popUpTo("dashboard") { inclusive = true }
                        }
                    },
                    shape = CircleShape,
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFC70039))
                ) {
                    Text(text = "Log Out", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }

                Button(
                    onClick = { /* Keep or leave summary action */ },
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = primaryPink, contentColor = Color.White)
                ) {
                    Text(text = "SoulSpace Active", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

@Composable
fun DashboardCard(title: String, description: String, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4A3B45)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                fontSize = 13.sp,
                color = Color.Gray
            )
        }
    }
}