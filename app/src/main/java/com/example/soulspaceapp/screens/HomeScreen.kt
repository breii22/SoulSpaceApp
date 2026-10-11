package com.example.soulspaceapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.soulspaceapp.models.UserViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: UserViewModel = viewModel()
) {
    var currentView by remember { mutableStateOf("home") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (currentView) {
            "home" -> MainHomeView(
                viewModel = viewModel,
                onNavigateToProfile = { currentView = "profile" },
                onNavigateToMood = { currentView = "mood" },
                onNavigateToRant = { currentView = "rant" },
                onNavigateToPet = { currentView = "pet" }
            )
            "profile" -> ProfileScreen(
                viewModel = viewModel,
                onBack = { currentView = "home" }
            )
            "mood" -> MoodSection(
                viewModel = viewModel,
                onBack = { currentView = "home" }
            )
            "rant" -> RantSection(
                viewModel = viewModel,
                onBack = { currentView = "home" }
            )
            "pet" -> PetSection(
                viewModel = viewModel,
                onBack = { currentView = "home" }
            )
        }
    }
}

@Composable
fun MainHomeView(
    viewModel: UserViewModel,
    onNavigateToProfile: () -> Unit,
    onNavigateToMood: () -> Unit,
    onNavigateToRant: () -> Unit,
    onNavigateToPet: () -> Unit
) {
    val currentMood by viewModel.mood.collectAsStateWithLifecycle()
    val petName by viewModel.petName.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hello There!",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Welcome to your SoulSpace.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "For Today's Quote ✨",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "\"Mistakes are not failures, they're lessons.\"")

                if (currentMood.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Current Mood: $currentMood")
                }

                if (petName.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = "Pet Name: $petName")
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "What would you like to do?",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = onNavigateToProfile,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("View My Profile")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onNavigateToMood,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("My Mood For Today")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onNavigateToRant,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Rant Dump")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onNavigateToPet,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Visit My Pet")
        }
    }
}

@Composable
fun MoodSection(viewModel: UserViewModel, onBack: () -> Unit) {
    val currentMood by viewModel.mood.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "How are you feeling?", style = MaterialTheme.typography.headlineSmall)
        Text("Choose the mood that best describes you today.")

        Spacer(modifier = Modifier.height(20.dp))

        listOf("Happy", "Sad", "Tired", "Angry").forEach { mood ->
            Button(
                onClick = { viewModel.selectMood(mood) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(mood)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        if (currentMood.isNotEmpty()) {
            Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(15.dp)) {
                Text(text = "Today's mood: $currentMood", modifier = Modifier.padding(20.dp))
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Back to Home")
        }
    }
}

@Composable
fun RantSection(viewModel: UserViewModel, onBack: () -> Unit) {
    val rantText by viewModel.rant.collectAsStateWithLifecycle()
    val rantMessage by viewModel.message.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Rant Dump ♡", style = MaterialTheme.typography.headlineSmall)
        Text("Let it all out. Write whatever is on your mind.")

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = rantText,
            onValueChange = { viewModel.onRantChange(it) },
            label = { Text("What's on your mind?") },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            shape = RoundedCornerShape(15.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = { viewModel.saveRant() },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Save Rant")
        }

        if (rantMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = rantMessage, style = MaterialTheme.typography.bodyLarge)
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Back to Home")
        }
    }
}

@Composable
fun PetSection(viewModel: UserViewModel, onBack: () -> Unit) {
    val petName by viewModel.petName.collectAsStateWithLifecycle()
    val petMessage by viewModel.petMessage.collectAsStateWithLifecycle()

    var newNameInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Your Comfort Pet", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "🐶", style = MaterialTheme.typography.displayLarge)
        Spacer(modifier = Modifier.height(10.dp))

        if (petName.isEmpty()) {
            Text("Give your pet a name!")
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = newNameInput,
                onValueChange = { newNameInput = it },
                label = { Text("Pet name") },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { viewModel.setPetName(newNameInput) },
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Name My Pet")
            }
        } else {
            Text(text = "Meet $petName! 🐾", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = petMessage.ifEmpty { "Hi! I'm $petName. I'm here for you no matter what, you can talk to me always. ♡" })
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { viewModel.getComfortMessage() },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Comfort Me")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { viewModel.getEncouragingMessage() },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Talk to $petName")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Back to Home")
        }
    }
}