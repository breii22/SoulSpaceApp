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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.soulspaceapp.viewmodel.UserViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: UserViewModel
) {
    val usrList by viewModel.usrList.collectAsState()
    val currentUsername by viewModel.currentUsername.collectAsState()

    val currentUser = usrList.find { it.username.equals(currentUsername, ignoreCase = true) }
        ?: usrList.lastOrNull()
    val profile = currentUser?.profile

    val deepPurplishDark = Color(0xFF3D314A)
    val softLavender = Color(0xFFCDB4DB)
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
                .padding(20.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "My Profile",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = deepPurplishDark
            )
            Text(
                text = "Your registered account details",
                fontSize = 13.sp,
                color = deepPurplishDark.copy(alpha = 0.6f)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    ProfileRow(label = "Username", value = currentUser?.username ?: "N/A")
                    ProfileRow(label = "First Name", value = profile?.firstName?.takeIf { it.isNotBlank() } ?: "N/A")
                    ProfileRow(label = "Middle Name", value = profile?.middleName?.takeIf { it.isNotBlank() } ?: "N/A")
                    ProfileRow(label = "Age", value = profile?.age?.takeIf { it.isNotBlank() } ?: "N/A")
                    ProfileRow(label = "Birthday", value = profile?.lastName?.takeIf { it.isNotBlank() } ?: "N/A")
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

@Composable
fun ProfileRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontWeight = FontWeight.Bold, color = Color(0xFF3D314A), fontSize = 14.sp)
        Text(text = value, color = Color.Gray, fontSize = 14.sp)
    }
}