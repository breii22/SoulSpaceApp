package com.example.soulspaceapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
fun PetScreen(
    navController: NavHostController,
    viewModel: UserViewModel
) {
    val petName by viewModel.petName.collectAsState()
    val petMessage by viewModel.petMessage.collectAsState()
    var tempNameInput by remember { mutableStateOf(petName) }

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
                    text = "Pet Companion",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = textDark
                )
                Text(
                    text = "Your virtual emotional support buddy",
                    fontSize = 13.sp,
                    color = textDark.copy(alpha = 0.6f)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Card(
                modifier = Modifier.fillMaxWidth(0.9f),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "🐾 ${petName.ifEmpty { "Buddy" }}",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = textDark
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = petMessage.ifEmpty { "Hello! I'm here to keep you company. Tap a button below!" },
                        fontSize = 14.sp,
                        color = textDark.copy(alpha = 0.8f),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(0.9f),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = { viewModel.getComfortMessage() },
                    modifier = Modifier.weight(1f),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = softLavender, contentColor = textDark)
                ) {
                    Text(text = "Get Comfort", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = { viewModel.getEncouragingMessage() },
                    modifier = Modifier.weight(1f),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = primaryPink, contentColor = Color.White)
                ) {
                    Text(text = "Get Cheers", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Column(
                modifier = Modifier.fillMaxWidth(0.9f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = tempNameInput,
                    onValueChange = { tempNameInput = it },
                    placeholder = { Text("Enter Pet Name", color = Color.Gray) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = CircleShape,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = inputBg,
                        unfocusedContainerColor = inputBg,
                        focusedBorderColor = primaryPink,
                        unfocusedBorderColor = Color.Transparent,
                        focusedTextColor = textDark,
                        unfocusedTextColor = textDark
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        if (tempNameInput.isNotBlank()) {
                            viewModel.setPetName(tempNameInput)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = textDark, contentColor = Color.White)
                ) {
                    Text(text = "Save Pet Name", fontWeight = FontWeight.Bold, fontSize = 16.sp)
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