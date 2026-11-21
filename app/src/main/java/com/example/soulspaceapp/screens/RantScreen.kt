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
fun RantScreen(
    navController: NavHostController,
    viewModel: UserViewModel
) {
    val rantText by viewModel.rant.collectAsState()
    val feedbackMessage by viewModel.message.collectAsState()

    val primaryPink = Color(0xFFD87093)
    val softLavender = Color(0xFFCDB4DB)
    val textDark = Color(0xFF4A3B45)
    val inputBg = Color(0xFFFFF5F7)
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFEEF2),
                        Color(0xFFF3E8FF)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier.fillMaxWidth(0.9f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Rant Dump",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = textDark
                )
                Text(
                    text = "Unload your thoughts freely",
                    fontSize = 13.sp,
                    color = textDark.copy(alpha = 0.6f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier.fillMaxWidth(0.9f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = rantText,
                    onValueChange = { viewModel.onRantChange(it) },
                    placeholder = { Text("What's on your mind today?", color = Color.Gray) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = inputBg,
                        unfocusedContainerColor = inputBg,
                        focusedBorderColor = primaryPink,
                        unfocusedBorderColor = Color.Transparent,
                        focusedTextColor = textDark,
                        unfocusedTextColor = textDark
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { viewModel.saveRant() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = primaryPink,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Save Rant", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }

                if (feedbackMessage.isNotBlank()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = feedbackMessage,
                        color = textDark,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Bottom-Right Go Back Button
            Row(
                modifier = Modifier.fillMaxWidth(0.9f),
                horizontalArrangement = Arrangement.End
            ) {
                OutlinedButton(
                    onClick = { navController.popBackStack() },
                    shape = CircleShape,
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = textDark
                    )
                ) {
                    Text(text = "Go Back", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }
            }
        }
    }
}