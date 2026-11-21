package com.example.soulspaceapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.soulspaceapp.viewmodel.UserViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: UserViewModel
) {
    val usrListState by viewModel.usrList.collectAsState()

    // Extract first name or fallback to username/guest
    val currentUser = usrListState.lastOrNull()
    val displayName = currentUser?.profile?.firstName?.takeIf { it.isNotBlank() }
        ?: currentUser?.username?.takeIf { it.isNotBlank() }
        ?: "Friend"

    val skyBlue = Color(0xFFA2D2FF)
    val deepPurplishDark = Color(0xFF3D314A)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFF8F0FC), Color(0xFFE8F1F5))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "SoulSpace",
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,
                color = deepPurplishDark
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Welcome, $displayName!\nThis is your personal safe space. Take a deep breath, reflect, and unwind.",
                fontSize = 15.sp,
                color = deepPurplishDark.copy(alpha = 0.75f),
                textAlign = TextAlign.Center,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    navController.navigate("dashboard") {
                        popUpTo("welcome") { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = skyBlue,
                    contentColor = deepPurplishDark
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp)
            ) {
                Text(
                    text = "Start Your Journey",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}