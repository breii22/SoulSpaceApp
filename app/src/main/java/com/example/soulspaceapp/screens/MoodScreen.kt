package com.example.soulspaceapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
fun MoodScreen(
    navController: NavHostController,
    viewModel: UserViewModel
) {
    val currentMood by viewModel.mood.collectAsState()
    val moods = listOf("Happy 😊", "Calm 🌿", "Anxious 🌧️", "Sad 💧", "Tired 🌙", "Angry 🔥")

    val deepPurplishDark = Color(0xFF3D314A)
    val skyBlue = Color(0xFFA2D2FF)
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFF8F0FC), Color(0xFFE8F1F5))
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Mood Tracker",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = deepPurplishDark
            )
            Text(
                text = "How are you feeling right now?",
                fontSize = 13.sp,
                color = deepPurplishDark.copy(alpha = 0.6f)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Centered Mood Cards List
            Column(
                modifier = Modifier.fillMaxWidth(0.9f),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                moods.forEach { moodOption ->
                    val isSelected = currentMood == moodOption
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.selectMood(moodOption) },
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) skyBlue else Color.White
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = moodOption,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = deepPurplishDark
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Bottom-Right Go Back Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                OutlinedButton(
                    onClick = { navController.popBackStack() },
                    shape = CircleShape,
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = deepPurplishDark
                    )
                ) {
                    Text(text = "Go Back", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }
            }
        }
    }
}